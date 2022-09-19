package com.fliurkevych.pdp.pdpspringcore.service;

import com.fliurkevych.pdp.pdpspringcore.exception.NotFoundException;
import com.fliurkevych.pdp.pdpspringcore.exception.ValidationException;
import com.fliurkevych.pdp.pdpspringcore.model.Event;
import com.fliurkevych.pdp.pdpspringcore.repository.EventRepository;
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
public class EventService {

  @Autowired
  private EventRepository eventRepository;

  public Event getEventById(Long eventId) {
    log.info("Getting event by id: {}", eventId);
    return eventRepository.getEventById(eventId)
      .orElseThrow(
        () -> new NotFoundException(
          String.format("Can not found element with key: [%s]", eventId)));
  }

  public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
    log.info("Getting events by title: {}", title);
    return eventRepository.getEventsByTitle(title, pageSize, pageNum);
  }

  public List<Event> getEventsByDate(Date date, int pageSize, int pageNum) {
    log.info("Getting events by date: {}", date);
    return eventRepository.getEventsForDay(date, pageSize, pageNum);
  }

  public Event createEvent(Event event) {
    log.info("Creating new event with title [{}], for date [{}]",
      event.getTitle(), event.getDate());
    var optional = eventRepository.getEventById(event.getId());
    if (optional.isEmpty()) {
      return eventRepository.save(event);
    } else {
      throw new ValidationException(
        String.format("Event with id [%s] have already created", event.getId()));
    }
  }

  public Event updateEvent(Event event) {
    log.info("Updating event with id [{}]", event.getId());
    var optional = eventRepository.getEventById(event.getId());
    if (optional.isPresent()) {
      return eventRepository.update(event);
    } else {
      throw new NotFoundException(
        String.format("Can not found element with key: [%s]", event.getId()));
    }
  }

  public boolean deleteEvent(Long eventId) {
    log.info("Deleting event with id [{}]", eventId);
    var optional = eventRepository.getEventById(eventId);
    if (optional.isPresent()) {
      return eventRepository.delete(eventId);
    } else {
      throw new NotFoundException(
        String.format("Can not found element with key: [%s]", eventId));
    }
  }

}
