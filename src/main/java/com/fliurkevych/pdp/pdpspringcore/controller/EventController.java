package com.fliurkevych.pdp.pdpspringcore.controller;

import com.fliurkevych.pdp.pdpspringcore.model.Event;
import com.fliurkevych.pdp.pdpspringcore.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
@RestController
@RequestMapping("/events")
public class EventController {

  @Autowired
  private EventService eventService;

  @GetMapping(path = "/search")
  public ResponseEntity<List<Event>> getEventsByTitle(@RequestParam String title,
    @RequestParam int pageSize,
    @RequestParam int pageNum) {
    return ResponseEntity.ok(eventService.getEventsByTitle(title, pageSize, pageNum));
  }

}
