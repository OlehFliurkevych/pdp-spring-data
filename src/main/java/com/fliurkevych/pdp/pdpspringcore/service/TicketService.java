package com.fliurkevych.pdp.pdpspringcore.service;

import static com.fliurkevych.pdp.pdpspringcore.converter.EventConverter.dtoToEntity;
import static com.fliurkevych.pdp.pdpspringcore.converter.TicketConverter.entityToDto;
import static com.fliurkevych.pdp.pdpspringcore.converter.UserConverter.dtoToEntity;
import static com.fliurkevych.pdp.pdpspringcore.util.ValidationUtils.validatePlaceNumber;

import com.fliurkevych.pdp.pdpspringcore.converter.TicketConverter;
import com.fliurkevych.pdp.pdpspringcore.dto.BookTicketDto;
import com.fliurkevych.pdp.pdpspringcore.dto.TicketDto;
import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import com.fliurkevych.pdp.pdpspringcore.storage.TicketStorage;
import com.fliurkevych.pdp.pdpspringcore.xml.TicketsXml;
import com.fliurkevych.pdp.pdpspringcore.xml.XmlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Service
public class TicketService {

  private final TicketStorage ticketStorage;
  private final UserService userService;
  private final EventService eventService;
  private final XmlService xmlService;
  private final UserAccountService userAccountService;

  public TicketService(TicketStorage ticketStorage, UserService userService,
    EventService eventService, XmlService xmlService,
    UserAccountService userAccountService) {
    this.ticketStorage = ticketStorage;
    this.userService = userService;
    this.eventService = eventService;
    this.xmlService = xmlService;
    this.userAccountService = userAccountService;
  }

  public TicketDto bookTicket(BookTicketDto bookTicketDto) {
    var userId = bookTicketDto.getUserId();
    var eventId = bookTicketDto.getEventId();
    var place = bookTicketDto.getPlace();
    log.info("User with id [{}] try to book ticket for event with id [{}]", userId, eventId);
    var user = userService.getUserById(userId);
    var event = eventService.getEventById(eventId);
    validatePlaceNumber(place);
    var userAccount = user.getUserAccount();
    userAccountService.update(userAccountService.reduceUserAccountBalance(userAccount, event));

    var ticket =
      new Ticket(null, dtoToEntity(event), dtoToEntity(user), place, bookTicketDto.getCategory());

    var saved = ticketStorage.save(ticket);
    log.info("Successfully booked ticket with id [{}]", saved.getId());

    return entityToDto(saved);
  }

  public List<TicketDto> getBookedTicketsByUserId(Long userId, Pageable pageable) {
    var user = userService.getUserById(userId);
    log.info("Getting all booked tickets for user with name [{}]", user.getName());
    return ticketStorage.getBookedTicketsForUser(userId, pageable).stream()
      .map(TicketConverter::entityToDto)
      .collect(Collectors.toList());
  }

  public List<TicketDto> getBookedTicketsByEventId(Long eventId, Pageable pageable) {
    var event = eventService.getEventById(eventId);
    log.info("Getting all booked tickets for event with title [{}]", event.getTitle());
    return ticketStorage.getBookedTicketsForEvent(eventId, pageable).stream()
      .map(TicketConverter::entityToDto)
      .collect(Collectors.toList());
  }

  public boolean cancelTicket(long ticketId) {
    log.info("Canceling ticket with id: [{}]", ticketId);
    return ticketStorage.delete(ticketId);
  }

  public boolean preloadTickets() {
    log.info("Preloading tickets");

    // TODO: You need to use resource-based file location here
    final var ticketsXmlFilePath =
      Objects.requireNonNull(getClass().getClassLoader().getResource("tickets.xml")).getPath();

    var ticketsXml = xmlService.unmarshal(ticketsXmlFilePath, TicketsXml.class);

    var tickets = ticketsXml.getTickets();

    var savedTickets = tickets.stream()
      .map(this::bookTicket)
      .collect(Collectors.toList());
    log.info("Successfully preloaded [{}] tickets", savedTickets.size());

    return savedTickets.size() == tickets.size();
  }

}
