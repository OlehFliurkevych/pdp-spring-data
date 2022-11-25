package com.fliurkevych.pdp.pdpspringcore.storage.db.impl;

import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import com.fliurkevych.pdp.pdpspringcore.storage.TicketStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
public class DbTicketStorage implements TicketStorage {

  private final TicketRepository ticketRepository;

  public DbTicketStorage(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  @Override
  public Ticket save(Ticket ticket) {
    return ticketRepository.save(ticket);
  }

  @Override
  public List<Ticket> getBookedTicketsForUser(Long userId, Pageable pageable) {
    return ticketRepository.findAllByUserId(userId, pageable);
  }

  @Override
  public List<Ticket> getBookedTicketsForEvent(Long eventId, Pageable pageable) {
    return ticketRepository.findAllByEventId(eventId, pageable);
  }

  @Override
  public boolean delete(Long ticketId) {
    ticketRepository.deleteById(ticketId);
    return !ticketRepository.existsById(ticketId);
  }
}
