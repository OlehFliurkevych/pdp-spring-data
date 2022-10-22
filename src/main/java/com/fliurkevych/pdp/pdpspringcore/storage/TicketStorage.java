package com.fliurkevych.pdp.pdpspringcore.storage;

import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
@Component
public interface TicketStorage {

  Ticket save(Ticket ticket);

  List<Ticket> getBookedTicketsForUser(Long userId, Pageable pageable);

  List<Ticket> getBookedTicketsForEvent(Long eventId, Pageable pageable);

  boolean delete(Long ticketId);

}
