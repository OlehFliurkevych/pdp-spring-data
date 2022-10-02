package com.fliurkevych.pdp.pdpspringcore.service;

import static com.fliurkevych.pdp.pdpspringcore.util.ValidationUtils.validatePlaceNumber;

import com.fliurkevych.pdp.pdpspringcore.converter.TicketConverter;
import com.fliurkevych.pdp.pdpspringcore.dto.BookTicketDto;
import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import com.fliurkevych.pdp.pdpspringcore.repository.TicketRepository;
import com.fliurkevych.pdp.pdpspringcore.xml.TicketsXml;
import com.fliurkevych.pdp.pdpspringcore.xml.XmlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Service
public class TicketService {

  private final Random random;
  private final TicketRepository ticketRepository;
  private final UserService userService;
  private final EventService eventService;
  private final XmlService xmlService;

  @Autowired
  public TicketService(TicketRepository ticketRepository, UserService userService,
    EventService eventService, XmlService xmlService) {
    this.random = new Random();
    this.ticketRepository = ticketRepository;
    this.userService = userService;
    this.eventService = eventService;
    this.xmlService = xmlService;
  }

  public Ticket bookTicket(BookTicketDto bookTicketDto) {
    var userId = bookTicketDto.getUserId();
    var eventId = bookTicketDto.getEventId();
    var place = bookTicketDto.getPlace();
    var category = bookTicketDto.getCategory();
    log.info("User with id [{}] try to book ticket for event with id [{}]", userId, eventId);
    var user = userService.getUserById(userId);
    var event = eventService.getEventById(eventId);
    validatePlaceNumber(place);

    var ticket =
      new Ticket((long) random.nextInt(1000), event.getId(), user.getId(), place, category);

    return ticketRepository.save(ticket);
  }

  public List<Ticket> getBookedTicketsByUserId(Long userId, Pageable pageable) {
    var user = userService.getUserById(userId);
    log.info("Getting all booked tickets for user with name [{}]", user.getName());
    return ticketRepository.getBookedTicketsForUser(userId, pageable.getPageSize(),
      pageable.getPageNumber());
  }

  public List<Ticket> getBookedTicketsByEventId(Long eventId, Pageable pageable) {
    var event = eventService.getEventById(eventId);
    log.info("Getting all booked tickets for event with title [{}]", event.getTitle());
    return ticketRepository.getBookedTicketsForEvent(eventId, pageable.getPageSize(),
      pageable.getPageNumber());
  }

  public boolean cancelTicket(long ticketId) {
    log.info("Canceling ticket with id: [{}]", ticketId);
    return ticketRepository.delete(ticketId);
  }

  public boolean preloadTickets() {
    log.info("Preloading tickets");
    var ticketsXml = xmlService.unmarshal("src/main/resources/tickets.xml", TicketsXml.class);

    var tickets = TicketConverter.toTickets(ticketsXml);

    var savedTickets = new ArrayList<>();
    for (int i = 0; i < tickets.size(); i++) {
      var ticket = tickets.get(i);
      ticket.setId((long) i);
      savedTickets.add(ticketRepository.save(ticket));
    }
    return savedTickets.size() == tickets.size();
  }

}
