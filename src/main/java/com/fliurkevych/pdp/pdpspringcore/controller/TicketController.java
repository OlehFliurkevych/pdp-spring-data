package com.fliurkevych.pdp.pdpspringcore.controller;

import com.fliurkevych.pdp.pdpspringcore.dto.BookTicketDto;
import com.fliurkevych.pdp.pdpspringcore.facade.BookingFacade;
import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
@RestController
@RequestMapping("/tickets")
public class TicketController {

  private final BookingFacade bookingFacade;

  @Autowired
  public TicketController(BookingFacade bookingFacade) {
    this.bookingFacade = bookingFacade;
  }

  @PostMapping(path = "/")
  public ResponseEntity<Ticket> bookTicket(@RequestBody BookTicketDto bookTicketDto) {
    return ResponseEntity.ok(bookingFacade.bookTicket(bookTicketDto));
  }

  @GetMapping(path = "/search/user/{userId}")
  public ResponseEntity<List<Ticket>> getBookedTicketsByUserId(@PathVariable Long userId,
    @PageableDefault Pageable pageable) {
    return ResponseEntity.ok(bookingFacade.getBookedTicketsByUserId(userId, pageable));
  }

  @GetMapping(path = "/search/event/{eventId}")
  public ResponseEntity<List<Ticket>> getBookedTicketsByEventId(@PathVariable Long eventId,
    @PageableDefault Pageable pageable) {
    return ResponseEntity.ok(bookingFacade.getBookedTicketsByEventId(eventId, pageable));
  }

  @DeleteMapping(path = "/{ticketId}")
  public ResponseEntity<String> cancelTicket(@PathVariable Long ticketId) {
    if (bookingFacade.cancelTicket(ticketId)) {
      return ResponseEntity.ok("Successfully canceled ticket");
    } else {
      return ResponseEntity.ok("Can not cancel ticket");
    }
  }

  @GetMapping(path = "/preload")
  public ResponseEntity<?> preloadTickets() {
    if (bookingFacade.preloadTickets()) {
      return ResponseEntity.ok("Successfully preloaded tickets");
    }
    return ResponseEntity.badRequest().body("Not all tickets preloaded");
  }

  @GetMapping(path = "/search/user/{userId}/report", produces = MediaType.APPLICATION_PDF_VALUE)
  public ResponseEntity<InputStreamResource> generatePdfBookedTicketsByUserId(
    @PathVariable Long userId,
    @PageableDefault Pageable pageable) {
    var bis = bookingFacade.generatePdfTicketReportForUser(userId, pageable);
    var headers = new HttpHeaders();
    headers.add("Content-Disposition", "inline; filename=tickets.pdf");

    return ResponseEntity
      .ok()
      .headers(headers)
      .contentType(MediaType.APPLICATION_PDF)
      .body(new InputStreamResource(bis));
  }

}
