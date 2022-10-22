package com.fliurkevych.pdp.pdpspringcore.service;

import static com.fliurkevych.pdp.pdpspringcore.util.CollectionUtils.zipWithIndex;
import static com.fliurkevych.pdp.pdpspringcore.util.ValidationUtils.validatePlaceNumber;

import com.fliurkevych.pdp.pdpspringcore.dto.BookTicketDto;
import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import com.fliurkevych.pdp.pdpspringcore.repository.TicketRepository;
import com.fliurkevych.pdp.pdpspringcore.xml.TicketsXml;
import com.fliurkevych.pdp.pdpspringcore.xml.XmlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

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
  private final Converter<TicketsXml, List<Ticket>> ticketConverter;

  @Autowired
  public TicketService(TicketRepository ticketRepository,
    UserService userService,
    EventService eventService,
    XmlService xmlService,
    Converter<TicketsXml, List<Ticket>> ticketConverter) {
    this.ticketConverter = ticketConverter;
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
    return ticketRepository.getBookedTicketsForUser(userId, pageable);
  }

  public List<Ticket> getBookedTicketsByEventId(Long eventId, Pageable pageable) {
    var event = eventService.getEventById(eventId);
    log.info("Getting all booked tickets for event with title [{}]", event.getTitle());
    return ticketRepository.getBookedTicketsForEvent(eventId, pageable);
  }

  public boolean cancelTicket(long ticketId) {
    log.info("Canceling ticket with id: [{}]", ticketId);
    return ticketRepository.delete(ticketId);
  }

  public boolean preloadTickets() {
    log.info("Preloading tickets");

    // TODO: You need to use resource-based file location here
    final var ticketsXmlFilePath =
      Objects.requireNonNull(getClass().getClassLoader().getResource("tickets.xml")).getPath();

    var ticketsXml = xmlService.unmarshal(ticketsXmlFilePath, TicketsXml.class);

    final var tickets = ticketConverter.convert(ticketsXml);

    var savedTickets = zipWithIndex(tickets).stream()
      .map(TicketService::setId)
      .map(ticketRepository::save)
      .collect(Collectors.toList());

    return savedTickets.size() == tickets.size();
  }

  private static Ticket setId(final Pair<Ticket, Integer> ticketWithIndex) {
    var ticket = ticketWithIndex.getFirst();
    var index = ticketWithIndex.getSecond();

    ticket.setId(Long.valueOf(index));
    return ticket;
  }
}
