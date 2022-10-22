package com.fliurkevych.pdp.pdpspringcore.storage.db;

import com.fliurkevych.pdp.pdpspringcore.model.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
@Repository
public interface DbEventRepository extends PagingAndSortingRepository<Event, Long> {
  
  List<Event> findAllByTitle(String title, Pageable pageable);
  List<Event> findAllByDate(Date date, Pageable pageable);

}
