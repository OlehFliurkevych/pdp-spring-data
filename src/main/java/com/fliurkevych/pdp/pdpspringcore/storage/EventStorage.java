package com.fliurkevych.pdp.pdpspringcore.storage;

import com.fliurkevych.pdp.pdpspringcore.model.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public interface EventStorage {

  Optional<Event> getEventById(Long eventId);

  List<Event> getEventsByTitle(String title, Pageable pageable);

  List<Event> getEventsForDay(Date day, Pageable pageable);

  Event save(Event event);

  Event update(Event event);

  boolean delete(Long eventId);

  Collection<Event> getAllEvents();

  boolean exists(Long id);
}
