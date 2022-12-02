package com.fliurkevych.pdp.pdpspringcore.storage.db.impl;

import com.fliurkevych.pdp.pdpspringcore.model.Event;
import com.fliurkevych.pdp.pdpspringcore.storage.EventStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
public class DbEventStorage implements EventStorage {

  private final EventRepository eventRepository;

  public DbEventStorage(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  public Optional<Event> getEventById(Long eventId) {
    return eventRepository.findById(eventId);
  }

  @Override
  public List<Event> getEventsByTitle(String title, Pageable pageable) {
    return eventRepository.findAllByTitle(title, pageable);
  }

  @Override
  public List<Event> getEventsForDay(Date day, Pageable pageable) {
    return eventRepository.findAllByDate(day, pageable);
  }

  @Override
  public Event save(Event event) {
    return eventRepository.save(event);
  }

  @Override
  public Event update(Event event) {
    return eventRepository.save(event);
  }

  @Override
  public boolean delete(Long eventId) {
    eventRepository.deleteById(eventId);
    return !eventRepository.existsById(eventId);
  }

  @Override
  public Collection<Event> getAllEvents() {

    return null;
  }
}
