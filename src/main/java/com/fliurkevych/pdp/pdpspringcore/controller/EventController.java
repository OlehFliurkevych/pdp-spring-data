package com.fliurkevych.pdp.pdpspringcore.controller;

import com.fliurkevych.pdp.pdpspringcore.facade.BookingFacade;
import com.fliurkevych.pdp.pdpspringcore.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@Controller
@RequestMapping("/events")
public class EventController {

  private final BookingFacade bookingFacade;

  @Autowired
  public EventController(BookingFacade bookingFacade) {
    this.bookingFacade = bookingFacade;
  }

  @GetMapping
  public String getAllEvents(Model model) {
    model.addAttribute("events", bookingFacade.getAllEvents());
    return "event-list";
  }

  @GetMapping(path = "/{eventId}")
  public ResponseEntity<Event> getEventById(@PathVariable Long eventId) {
    return ResponseEntity.ok(bookingFacade.getEventById(eventId));
  }

  @GetMapping(path = "/search/title")
  public ResponseEntity<List<Event>> getEventsByTitle(@RequestParam String title,
    @PageableDefault Pageable pageable) {
    return ResponseEntity.ok(bookingFacade.getEventsByTitle(title, pageable));
  }

  @GetMapping(path = "/search/date")
  public ResponseEntity<List<Event>> getEventsByDate(@RequestParam Date date,
    @PageableDefault Pageable pageable) {
    return ResponseEntity.ok(bookingFacade.getEventsForDay(date, pageable));
  }

  @PostMapping(path = "/")
  public ResponseEntity<Event> createEvent(@RequestBody Event event) {
    return ResponseEntity.ok(bookingFacade.createEvent(event));
  }

  @PutMapping(path = "/")
  public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
    return ResponseEntity.ok(bookingFacade.updateEvent(event));
  }

  @DeleteMapping(path = "/{eventId}")
  public ResponseEntity<Boolean> deleteEvent(@PathVariable Long eventId) {
    return ResponseEntity.ok(bookingFacade.deleteEvent(eventId));
  }

}
