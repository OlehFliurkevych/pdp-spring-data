package com.fliurkevych.pdp.pdpspringcore.repository;

import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import com.fliurkevych.pdp.pdpspringcore.storage.TicketStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Component
public class TicketRepository {

  private final TicketStorage ticketStorage;

  @Autowired
  public TicketRepository(TicketStorage ticketStorage) {
    this.ticketStorage = ticketStorage;
  }

  public Ticket save(Ticket ticket) {
    return ticketStorage.save(ticket);
  }

  public List<Ticket> getBookedTicketsForUser(Long userId, Pageable pageable) {
    return ticketStorage.getBookedTicketsForUser(userId, pageable);
  }

  public List<Ticket> getBookedTicketsForEvent(Long eventId, Pageable pageable) {
    return ticketStorage.getBookedTicketsForEvent(eventId, pageable);
  }

  public boolean delete(Long ticketId) {
    return ticketStorage.delete(ticketId);
  }

}
