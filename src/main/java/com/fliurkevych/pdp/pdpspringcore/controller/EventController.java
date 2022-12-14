package com.fliurkevych.pdp.pdpspringcore.controller;

import com.fliurkevych.pdp.pdpspringcore.dto.EventDto;
import com.fliurkevych.pdp.pdpspringcore.facade.BookingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
@RestController
@RequestMapping("/events")
public class EventController {

  private final BookingFacade bookingFacade;

  @Autowired
  public EventController(BookingFacade bookingFacade) {
    this.bookingFacade = bookingFacade;
  }

  @GetMapping
  public ResponseEntity<List<EventDto>> getAllEvents() {
    return ResponseEntity.ok(bookingFacade.getAllEvents());
  }

  @GetMapping(path = "/{eventId}")
  public ResponseEntity<EventDto> getEventById(@PathVariable Long eventId) {
    return ResponseEntity.ok(bookingFacade.getEventById(eventId));
  }

  @GetMapping(path = "/search/title")
  public ResponseEntity<List<EventDto>> getEventsByTitle(@RequestParam String title,
    @PageableDefault Pageable pageable) {
    return ResponseEntity.ok(bookingFacade.getEventsByTitle(title, pageable));
  }

  @GetMapping(path = "/search/date")
  public ResponseEntity<List<EventDto>> getEventsByDate(@RequestParam Date date,
    @PageableDefault Pageable pageable) {
    return ResponseEntity.ok(bookingFacade.getEventsForDay(date, pageable));
  }

  @PostMapping(path = "/")
  public ResponseEntity<EventDto> createEvent(@RequestBody EventDto event) {
    return ResponseEntity.ok(bookingFacade.createEvent(event));
  }

  @PutMapping(path = "/")
  public ResponseEntity<EventDto> updateEvent(@RequestBody EventDto event) {
    return ResponseEntity.ok(bookingFacade.updateEvent(event));
  }

  @DeleteMapping(path = "/{eventId}")
  public ResponseEntity<Boolean> deleteEvent(@PathVariable Long eventId) {
    return ResponseEntity.ok(bookingFacade.deleteEvent(eventId));
  }

}
