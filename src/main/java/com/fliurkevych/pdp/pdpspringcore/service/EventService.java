package com.fliurkevych.pdp.pdpspringcore.service;

import com.fliurkevych.pdp.pdpspringcore.exception.NotFoundException;
import com.fliurkevych.pdp.pdpspringcore.exception.ValidationException;
import com.fliurkevych.pdp.pdpspringcore.model.Event;
import com.fliurkevych.pdp.pdpspringcore.storage.EventStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Service
public class EventService {

  private final EventStorage eventStorage;

  @Autowired
  public EventService(EventStorage eventStorage) {
    this.eventStorage = eventStorage;
  }

  public Event getEventById(Long eventId) {
    log.info("Getting event by id: {}", eventId);
    return eventStorage.getEventById(eventId)
      .orElseThrow(
        () -> new NotFoundException(
          String.format("Can not found element with key: [%s]", eventId)));
  }

  public List<Event> getEventsByTitle(String title, Pageable pageable) {
    log.info("Getting events by title: {}", title);
    return eventStorage.getEventsByTitle(title, pageable);
  }

  public List<Event> getEventsByDate(Date date, Pageable pageable) {
    log.info("Getting events by date: {}", date);
    return eventStorage.getEventsForDay(date, pageable);
  }

  public Event createEvent(Event event) {
    log.info("Creating new event with title [{}], for date [{}]",
      event.getTitle(), event.getDate());

    // TODO: I'd suggest to add a sort of exists(event.getId()) method to the repository
    if (eventStorage.getEventById(event.getId()).isEmpty()) {
      return eventStorage.save(event);
    } else {
      throw new ValidationException(
        String.format("Event with id [%s] have already created", event.getId()));
    }
  }

  public Event updateEvent(Event event) {
    log.info("Updating event with id [{}]", event.getId());

    return eventStorage.getEventById(event.getId())
      .map(eventStorage::update)
      .orElseThrow(() -> new NotFoundException(
        String.format("Can not found element with key: [%s]", event.getId())));
  }

  public boolean deleteEvent(Long eventId) {
    log.info("Deleting event with id [{}]", eventId);

    return eventStorage.getEventById(eventId)
      .map(event -> eventStorage.delete(event.getId()))
      .orElseThrow(() -> new NotFoundException(
        String.format("Can not found element with key: [%s]", eventId)));
  }

  public List<Event> getAllEvents() {
    log.info("Getting all events");

    return new ArrayList<>(eventStorage.getAllEvents());
  }

}
