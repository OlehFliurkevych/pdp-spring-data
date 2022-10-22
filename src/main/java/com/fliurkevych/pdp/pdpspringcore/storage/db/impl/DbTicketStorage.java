package com.fliurkevych.pdp.pdpspringcore.storage.db.impl;

import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import com.fliurkevych.pdp.pdpspringcore.storage.TicketStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.DbTicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Service
public class DbTicketStorage implements TicketStorage {

  private final DbTicketRepository dbTicketRepository;

  public DbTicketStorage(DbTicketRepository dbTicketRepository) {
    this.dbTicketRepository = dbTicketRepository;
  }

  @Override
  public Ticket save(Ticket ticket) {
    return dbTicketRepository.save(ticket);
  }

  @Override
  public List<Ticket> getBookedTicketsForUser(Long userId, Pageable pageable) {
    return dbTicketRepository.findAllByUserId(userId, pageable);
  }

  @Override
  public List<Ticket> getBookedTicketsForEvent(Long eventId, Pageable pageable) {
    return dbTicketRepository.findAllByEventId(eventId, pageable);
  }

  @Override
  public boolean delete(Long ticketId) {
    dbTicketRepository.deleteById(ticketId);
    return !dbTicketRepository.existsById(ticketId);
  }
}
