package com.fliurkevych.pdp.pdpspringcore.facade.impl;

import com.fliurkevych.pdp.pdpspringcore.dto.BookTicketDto;
import com.fliurkevych.pdp.pdpspringcore.dto.CreateUserAccountDto;
import com.fliurkevych.pdp.pdpspringcore.dto.EventDto;
import com.fliurkevych.pdp.pdpspringcore.dto.TicketDto;
import com.fliurkevych.pdp.pdpspringcore.dto.UserAccountDto;
import com.fliurkevych.pdp.pdpspringcore.dto.UserDto;
import com.fliurkevych.pdp.pdpspringcore.facade.BookingFacade;
import com.fliurkevych.pdp.pdpspringcore.service.EventService;
import com.fliurkevych.pdp.pdpspringcore.service.TicketService;
import com.fliurkevych.pdp.pdpspringcore.service.UserAccountService;
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
  private final UserAccountService userAccountService;

  @Autowired
  public BookingFacadeImpl(UserService userService, TicketService ticketService,
    EventService eventService, UserAccountService userAccountService) {
    this.userService = userService;
    this.ticketService = ticketService;
    this.eventService = eventService;
    this.userAccountService = userAccountService;
  }

  @Override
  public EventDto getEventById(long eventId) {
    return eventService.getEventById(eventId);
  }

  @Override
  public List<EventDto> getEventsByTitle(String title, Pageable pageable) {
    return eventService.getEventsByTitle(title, pageable);
  }

  @Override
  public List<EventDto> getEventsForDay(Date day, Pageable pageable) {
    return eventService.getEventsByDate(day, pageable);
  }

  @Override
  public EventDto createEvent(EventDto event) {
    return eventService.create(event);
  }

  @Override
  public EventDto updateEvent(EventDto event) {
    return eventService.update(event);
  }

  @Override
  public boolean deleteEvent(long eventId) {
    return eventService.delete(eventId);
  }

  @Override
  public UserDto getUserById(long userId) {
    return userService.getUserById(userId);
  }

  @Override
  public UserDto getUserByEmail(String email) {
    return userService.getUserByEmail(email);
  }

  @Override
  public List<UserDto> getUsersByName(String name, Pageable pageable) {
    return userService.getUsersByName(name, pageable);
  }

  @Override
  public UserDto createUser(UserDto userDto) {
    return userService.create(userDto);
  }

  @Override
  public UserDto updateUser(UserDto user) {
    return userService.update(user);
  }

  @Override
  public boolean deleteUser(long userId) {
    return userService.delete(userId);
  }

  @Override
  public TicketDto bookTicket(BookTicketDto bookTicketDto) {
    return ticketService.bookTicket(bookTicketDto);
  }

  @Override
  public List<TicketDto> getBookedTicketsByUserId(Long userId, Pageable pageable) {
    return ticketService.getBookedTicketsByUserId(userId, pageable);
  }

  @Override
  public List<TicketDto> getBookedTicketsByEventId(Long eventId, Pageable pageable) {
    return ticketService.getBookedTicketsByEventId(eventId, pageable);
  }

  @Override
  public boolean cancelTicket(long ticketId) {
    return ticketService.cancelTicket(ticketId);
  }

  @Override
  public List<UserDto> getAllUsers() {
    return userService.getAllUsers();
  }

  @Override
  public List<EventDto> getAllEvents() {
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

  @Override
  public UserAccountDto createUserAccount(CreateUserAccountDto createUserAccountDto) {
    return userAccountService.create(createUserAccountDto);
  }

  @Override
  public UserAccountDto updateUserAccount(UserAccountDto userAccountDto) {
    return userAccountService.update(userAccountDto);
  }
}
