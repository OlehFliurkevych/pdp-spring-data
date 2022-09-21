package com.fliurkevych.pdp.pdpspringcore.repository;

import com.fliurkevych.pdp.pdpspringcore.model.Event;
import com.fliurkevych.pdp.pdpspringcore.storage.EventStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Component
public class EventRepository {

  private final EventStorage storage;

  @Autowired
  public EventRepository(EventStorage storage) {
    this.storage = storage;
  }

  public Optional<Event> getEventById(Long eventId) {
    return storage.getEventById(eventId);
  }

  public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
    return storage.getEventsByTitle(title, pageSize, pageNum);
  }

  public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
    return storage.getEventsForDay(day, pageSize, pageNum);
  }

  public Event save(Event event) {
    return storage.save(event);
  }

  public Event update(Event event) {
    return storage.update(event);
  }

  public boolean delete(Long eventId) {
    return storage.delete(eventId);
  }
}
