package com.fliurkevych.pdp.pdpspringcore.service;

import static com.fliurkevych.pdp.pdpspringcore.util.ValidationUtils.validatePlaceNumber;

import com.fliurkevych.pdp.pdpspringcore.enums.TicketCategory;
import com.fliurkevych.pdp.pdpspringcore.model.Event;
import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import com.fliurkevych.pdp.pdpspringcore.model.User;
import com.fliurkevych.pdp.pdpspringcore.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Service
public class TicketService {

  private final Random random;

  @Autowired
  private TicketRepository ticketRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private EventService eventService;

  public TicketService() {
    this.random = new Random();
  }

  public Ticket bookTicket(long userId, long eventId, int place, TicketCategory category) {
    log.info("User with id [{}] try to book ticket for event with id [{}]", userId, eventId);
    var user = userService.getUserById(userId);
    var event = eventService.getEventById(eventId);
    validatePlaceNumber(place);

    var ticket =
      new Ticket((long) random.nextInt(1000), event.getId(), user.getId(), place, category);

    return ticketRepository.save(ticket);
  }

  public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
    log.info("Getting all booked tickets for user with name [{}]", user.getName());
    var u = userService.getUserById(user.getId());
    return ticketRepository.getBookedTicketsForUser(u.getId(), pageSize, pageNum);
  }

  public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
    log.info("Getting all booked tickets for event with title [{}]", event.getTitle());
    var e = eventService.getEventById(event.getId());
    return ticketRepository.getBookedTicketsForEvent(e.getId(), pageSize, pageNum);
  }

  public boolean cancelTicket(long ticketId) {
    log.info("Canceling ticket with id: [{}]", ticketId);
    return ticketRepository.delete(ticketId);
  }


}
