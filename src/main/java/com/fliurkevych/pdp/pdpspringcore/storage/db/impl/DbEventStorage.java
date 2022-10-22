package com.fliurkevych.pdp.pdpspringcore.storage.db.impl;

import com.fliurkevych.pdp.pdpspringcore.model.Event;
import com.fliurkevych.pdp.pdpspringcore.storage.EventStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.DbEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Service
public class DbEventStorage implements EventStorage {

  private final DbEventRepository dbEventRepository;

  @Autowired
  public DbEventStorage(DbEventRepository dbEventRepository) {
    this.dbEventRepository = dbEventRepository;
  }

  @Override
  public Optional<Event> getEventById(Long eventId) {
    return dbEventRepository.findById(eventId);
  }

  @Override
  public List<Event> getEventsByTitle(String title, Pageable pageable) {
    return dbEventRepository.findAllByTitle(title, pageable);
  }

  @Override
  public List<Event> getEventsForDay(Date day, Pageable pageable) {
    return dbEventRepository.findAllByDate(day, pageable);
  }

  @Override
  public Event save(Event event) {
    return dbEventRepository.save(event);
  }

  @Override
  public Event update(Event event) {
    return dbEventRepository.save(event);
  }

  @Override
  public boolean delete(Long eventId) {
    dbEventRepository.deleteById(eventId);
    return !dbEventRepository.existsById(eventId);
  }

  @Override
  public Collection<Event> getAllEvents() {
    return dbEventRepository.findAll(Pageable.ofSize(Integer.MAX_VALUE)).getContent();
  }
}
