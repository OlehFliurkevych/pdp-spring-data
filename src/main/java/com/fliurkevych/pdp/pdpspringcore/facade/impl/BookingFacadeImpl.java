package com.fliurkevych.pdp.pdpspringcore.facade.impl;

import com.fliurkevych.pdp.pdpspringcore.dto.BookTicketDto;
import com.fliurkevych.pdp.pdpspringcore.facade.BookingFacade;
import com.fliurkevych.pdp.pdpspringcore.model.Event;
import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import com.fliurkevych.pdp.pdpspringcore.model.User;
import com.fliurkevych.pdp.pdpspringcore.service.EventService;
import com.fliurkevych.pdp.pdpspringcore.service.TicketService;
import com.fliurkevych.pdp.pdpspringcore.service.UserService;
import com.fliurkevych.pdp.pdpspringcore.util.PdfUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Service
public class BookingFacadeImpl implements BookingFacade {

  private final UserService userService;
  private final TicketService ticketService;
  private final EventService eventService;

  @Autowired
  public BookingFacadeImpl(UserService userService, TicketService ticketService,
    EventService eventService) {
    this.userService = userService;
    this.ticketService = ticketService;
    this.eventService = eventService;
  }

  @Override
  public Event getEventById(long eventId) {
    return eventService.getEventById(eventId);
  }

  @Override
  public List<Event> getEventsByTitle(String title, Pageable pageable) {
    return eventService.getEventsByTitle(title, pageable);
  }

  @Override
  public List<Event> getEventsForDay(Date day, Pageable pageable) {
    return eventService.getEventsByDate(day, pageable);
  }

  @Override
  public Event createEvent(Event event) {
    return eventService.createEvent(event);
  }

  @Override
  public Event updateEvent(Event event) {
    return eventService.updateEvent(event);
  }

  @Override
  public boolean deleteEvent(long eventId) {
    return eventService.deleteEvent(eventId);
  }

  @Override
  public User getUserById(long userId) {
    return userService.getUserById(userId);
  }

  @Override
  public User getUserByEmail(String email) {
    return userService.getUserByEmail(email);
  }

  @Override
  public List<User> getUsersByName(String name, Pageable pageable) {
    return userService.getUsersByName(name, pageable);
  }

  @Override
  public User createUser(User user) {
    return userService.createUser(user);
  }

  @Override
  public User updateUser(User user) {
    return userService.updateUser(user);
  }

  @Override
  public boolean deleteUser(long userId) {
    return userService.deleteUser(userId);
  }

  @Override
  public Ticket bookTicket(BookTicketDto bookTicketDto) {
    return ticketService.bookTicket(bookTicketDto);
  }

  @Override
  public List<Ticket> getBookedTicketsByUserId(Long userId, Pageable pageable) {
    return ticketService.getBookedTicketsByUserId(userId, pageable);
  }

  @Override
  public List<Ticket> getBookedTicketsByEventId(Long eventId, Pageable pageable) {
    return ticketService.getBookedTicketsByEventId(eventId, pageable);
  }

  @Override
  public boolean cancelTicket(long ticketId) {
    return ticketService.cancelTicket(ticketId);
  }

  @Override
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @Override
  public List<Event> getAllEvents() {
    return eventService.getAllEvents();
  }

  @Override
  public boolean preloadTickets() {
    return ticketService.preloadTickets();
  }

  @Override
  public ByteArrayInputStream generatePdfTicketReportForUser(Long userId, Pageable pageable) {
    var tickets = ticketService.getBookedTicketsByUserId(userId, pageable);
    return PdfUtils.generateTicketsReport(tickets);
  }
}
