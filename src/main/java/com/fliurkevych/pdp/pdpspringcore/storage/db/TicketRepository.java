package com.fliurkevych.pdp.pdpspringcore.storage.db;

import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
@Repository
public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {

  List<Ticket> findAllByUserId(Long userId, Pageable pageable);

  List<Ticket> findAllByEventId(Long eventId, Pageable pageable);


}
