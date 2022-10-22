package com.fliurkevych.pdp.pdpspringcore.service;

import com.fliurkevych.pdp.pdpspringcore.exception.NotFoundException;
import com.fliurkevych.pdp.pdpspringcore.exception.ValidationException;
import com.fliurkevych.pdp.pdpspringcore.model.Event;
import com.fliurkevych.pdp.pdpspringcore.repository.EventRepository;
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

  private final EventRepository eventRepository;

  @Autowired
  public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  public Event getEventById(Long eventId) {
    log.info("Getting event by id: {}", eventId);
    return eventRepository.getEventById(eventId)
      .orElseThrow(
        () -> new NotFoundException(
          String.format("Can not found element with key: [%s]", eventId)));
  }

  public List<Event> getEventsByTitle(String title, Pageable pageable) {
    log.info("Getting events by title: {}", title);
    return eventRepository.getEventsByTitle(title, pageable);
  }

  public List<Event> getEventsByDate(Date date, Pageable pageable) {
    log.info("Getting events by date: {}", date);
    return eventRepository.getEventsForDay(date, pageable);
  }

  public Event createEvent(Event event) {
    log.info("Creating new event with title [{}], for date [{}]",
      event.getTitle(), event.getDate());

    // TODO: I'd suggest to add a sort of exists(event.getId()) method to the repository
    if (eventRepository.getEventById(event.getId()).isEmpty()) {
      return eventRepository.save(event);
    } else {
      throw new ValidationException(
        String.format("Event with id [%s] have already created", event.getId()));
    }
  }

  public Event updateEvent(Event event) {
    log.info("Updating event with id [{}]", event.getId());

    return eventRepository.getEventById(event.getId())
      .map(eventRepository::update)
      .orElseThrow(() -> new NotFoundException(
        String.format("Can not found element with key: [%s]", event.getId())));
  }

  public boolean deleteEvent(Long eventId) {
    log.info("Deleting event with id [{}]", eventId);

    return eventRepository.getEventById(eventId)
      .map(event -> eventRepository.delete(event.getId()))
      .orElseThrow(() -> new NotFoundException(
        String.format("Can not found element with key: [%s]", eventId)));
  }
  
  public List<Event> getAllEvents(){
    log.info("Getting all events");
    
    return new ArrayList<>(eventRepository.getAllEvents());
  }

}
