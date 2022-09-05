package com.fliurkevych.pdp.pdpspringcore.facade.impl;

import com.fliurkevych.pdp.pdpspringcore.enums.TicketCategory;
import com.fliurkevych.pdp.pdpspringcore.facade.BookingFacade;
import com.fliurkevych.pdp.pdpspringcore.model.Event;
import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import com.fliurkevych.pdp.pdpspringcore.model.User;
import com.fliurkevych.pdp.pdpspringcore.service.EventService;
import com.fliurkevych.pdp.pdpspringcore.service.TicketService;
import com.fliurkevych.pdp.pdpspringcore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Service
public class BookingFacadeImpl implements BookingFacade {

  private UserService userService;
  private TicketService ticketService;
  private EventService eventService;

  @Autowired
  public BookingFacadeImpl(UserService userService,
    TicketService ticketService, EventService eventService) {
    this.userService = userService;
    this.ticketService = ticketService;
    this.eventService = eventService;
  }

  @Override
  public Event getEventById(long eventId) {
    return null;
  }

  @Override
  public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
    return null;
  }

  @Override
  public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
    return null;
  }

  @Override
  public Event createEvent(Event event) {
    return null;
  }

  @Override
  public Event updateEvent(Event event) {
    return null;
  }

  @Override
  public boolean deleteEvent(long eventId) {
    return false;
  }

  @Override
  public User getUserById(long userId) {
    return null;
  }

  @Override
  public User getUserByEmail(String email) {
    return null;
  }

  @Override
  public List<User> getUsersByName(String name, int pageSize, int pageNum) {
    return null;
  }

  @Override
  public User createUser(User user) {
    return null;
  }

  @Override
  public User updateUser(User user) {
    return null;
  }

  @Override
  public boolean deleteUser(long userId) {
    return false;
  }

  @Override
  public Ticket bookTicket(long userId, long eventId, int place, TicketCategory category) {
    return null;
  }

  @Override
  public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
    return null;
  }

  @Override
  public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
    return null;
  }

  @Override
  public boolean cancelTicket(long ticketId) {
    return false;
  }
}
