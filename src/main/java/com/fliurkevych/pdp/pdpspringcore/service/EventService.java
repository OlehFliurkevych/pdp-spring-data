package com.fliurkevych.pdp.pdpspringcore.service;

import static com.fliurkevych.pdp.pdpspringcore.converter.EventConverter.dtoToEntity;
import static com.fliurkevych.pdp.pdpspringcore.converter.EventConverter.entityToDto;

import com.fliurkevych.pdp.pdpspringcore.converter.EventConverter;
import com.fliurkevych.pdp.pdpspringcore.dto.EventDto;
import com.fliurkevych.pdp.pdpspringcore.exception.NotFoundException;
import com.fliurkevych.pdp.pdpspringcore.exception.ValidationException;
import com.fliurkevych.pdp.pdpspringcore.storage.EventStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

  public EventDto getEventById(Long eventId) {
    log.info("Getting event by id: {}", eventId);
    return eventStorage.getEventById(eventId)
      .map(EventConverter::entityToDto)
      .orElseThrow(
        () -> new NotFoundException(
          String.format("Can not found element with key: [%s]", eventId)));
  }

  public List<EventDto> getEventsByTitle(String title, Pageable pageable) {
    log.info("Getting events by title: {}", title);
    return eventStorage.getEventsByTitle(title, pageable).stream()
      .map(EventConverter::entityToDto)
      .collect(Collectors.toList());
  }

  public List<EventDto> getEventsByDate(Date date, Pageable pageable) {
    log.info("Getting events by date: {}", date);
    return eventStorage.getEventsForDay(date, pageable).stream()
      .map(EventConverter::entityToDto)
      .collect(Collectors.toList());
  }

  public EventDto create(EventDto event) {
    log.info("Creating new event with title [{}], for date [{}]",
      event.getTitle(), event.getDate());

    if (!eventStorage.exists(event.getId())) {
      var created = eventStorage.save(dtoToEntity(event));
      return entityToDto(created);
    } else {
      throw new ValidationException("Event with id [%s] have already created", event.getId());
    }
  }

  public EventDto update(EventDto event) {
    log.info("Updating event with id [{}]", event.getId());

    return eventStorage.getEventById(event.getId())
      .map(eventStorage::update)
      .map(EventConverter::entityToDto)
      .orElseThrow(() -> new NotFoundException(
        String.format("Can not found element with key: [%s]", event.getId())));
  }

  public boolean delete(Long eventId) {
    log.info("Deleting event with id [{}]", eventId);

    return eventStorage.getEventById(eventId)
      .map(event -> eventStorage.delete(event.getId()))
      .orElseThrow(() -> new NotFoundException(
        String.format("Can not found element with key: [%s]", eventId)));
  }

  public List<EventDto> getAllEvents() {
    log.info("Getting all events");
    return eventStorage.getAllEvents().stream()
      .map(EventConverter::entityToDto)
      .collect(Collectors.toList());
  }

}
