package com.fliurkevych.pdp.pdpspringcore.facade;

import com.fliurkevych.pdp.pdpspringcore.dto.BookTicketDto;
import com.fliurkevych.pdp.pdpspringcore.dto.CreateUserAccountDto;
import com.fliurkevych.pdp.pdpspringcore.dto.EventDto;
import com.fliurkevych.pdp.pdpspringcore.dto.TicketDto;
import com.fliurkevych.pdp.pdpspringcore.dto.UserAccountDto;
import com.fliurkevych.pdp.pdpspringcore.dto.UserDto;
import com.fliurkevych.pdp.pdpspringcore.model.Event;
import com.fliurkevych.pdp.pdpspringcore.model.User;
import org.springframework.data.domain.Pageable;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

/**
 * Groups together all operations related to tickets booking.
 */
public interface BookingFacade {

  /**
   * Gets event by its id.
   *
   * @return Event.
   */
  EventDto getEventById(long eventId);

  /**
   * Get list of events by matching title. Title is matched using 'contains' approach. In case
   * nothing was found, empty list is returned.
   *
   * @param title Event title or it's part.
   * @param pageable {@link Pageable}
   * @return List of events.
   */
  List<EventDto> getEventsByTitle(String title, Pageable pageable);

  /**
   * Get list of events for specified day. In case nothing was found, empty list is returned.
   *
   * @param day Date object from which day information is extracted.
   * @param pageable {@link Pageable}
   * @return List of events.
   */
  List<EventDto> getEventsForDay(Date day, Pageable pageable);

  /**
   * Creates new event. Event id should be auto-generated.
   *
   * @param event Event data.
   * @return Created Event object.
   */
  EventDto createEvent(EventDto event);

  /**
   * Updates event using given data.
   *
   * @param event Event data for update. Should have id set.
   * @return Updated Event object.
   */
  EventDto updateEvent(EventDto event);

  /**
   * Deletes event by its id.
   *
   * @param eventId Event id.
   * @return Flag that shows whether event has been deleted.
   */
  boolean deleteEvent(long eventId);

  /**
   * Gets user by its id.
   *
   * @return User.
   */
  UserDto getUserById(long userId);

  /**
   * Gets user by its email. Email is strictly matched.
   *
   * @return User.
   */
  UserDto getUserByEmail(String email);

  /**
   * Get list of users by matching name. Name is matched using 'contains' approach. In case nothing
   * was found, empty list is returned.
   *
   * @param name Users name or it's part.
   * @param pageable {@link Pageable}
   * @return List of users.
   */
  List<UserDto> getUsersByName(String name, Pageable pageable);

  /**
   * Creates new user. User id should be auto-generated.
   *
   * @param userDto User data.
   * @return Created User object.
   */
  UserDto createUser(UserDto userDto);

  /**
   * Updates user using given data.
   *
   * @param user User data for update. Should have id set.
   * @return Updated User object.
   */
  UserDto updateUser(UserDto user);

  /**
   * Deletes user by its id.
   *
   * @param userId User id.
   * @return Flag that shows whether user has been deleted.
   */
  boolean deleteUser(long userId);

  /**
   * Book ticket for a specified event on behalf of specified user.
   *
   * @param bookTicketDto {@link BookTicketDto}
   * @return Booked ticket object.
   * @throws java.lang.IllegalStateException if this place has already been booked.
   */
  TicketDto bookTicket(BookTicketDto bookTicketDto);

  /**
   * Get all booked tickets for specified user. Tickets should be sorted by event date in descending
   * order.
   *
   * @param userId id of user
   * @param pageable {@link Pageable}
   * @return List of Ticket objects.
   */
  List<TicketDto> getBookedTicketsByUserId(Long userId, Pageable pageable);

  /**
   * Get all booked tickets for specified event. Tickets should be sorted in by user email in
   * ascending order.
   *
   * @param eventId id of event
   * @param pageable {@link Pageable}
   * @return List of Ticket objects.
   */
  List<TicketDto> getBookedTicketsByEventId(Long eventId, Pageable pageable);

  /**
   * Cancel ticket with a specified id.
   *
   * @param ticketId Ticket id.
   * @return Flag whether anything has been canceled.
   */
  boolean cancelTicket(long ticketId);

  /**
   * Get all users
   *
   * @return List of {@link User} objects.
   */
  List<UserDto> getAllUsers();

  /**
   * Get all events
   *
   * @return List of {@link Event} objects.
   */
  List<EventDto> getAllEvents();

  /**
   * Populate tickets storage based on predefined file
   */
  boolean preloadTickets();

  /**
   * Generate pdf report booked tickets for user
   *
   * @return pdf report
   */
  ByteArrayInputStream generatePdfTicketReportForUser(Long userId, Pageable pageable);

  /**
   * Creates new user account.
   *
   * @param createUserAccountDto User Account data
   * @return Created User Account dto object.
   */
  UserAccountDto createUserAccount(CreateUserAccountDto createUserAccountDto);

  UserAccountDto updateUserAccount(UserAccountDto userAccountDto);

}
