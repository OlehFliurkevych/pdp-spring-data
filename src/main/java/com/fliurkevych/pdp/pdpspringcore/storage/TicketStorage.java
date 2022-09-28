package com.fliurkevych.pdp.pdpspringcore.storage;

import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
@Component
public interface TicketStorage {

  Ticket save(Ticket ticket);

  List<Ticket> getBookedTicketsForUser(Long userId, int pageSize, int pageNum);

  List<Ticket> getBookedTicketsForEvent(Long eventId, int pageSize, int pageNum);

  boolean delete(Long ticketId);

}
