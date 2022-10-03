package com.fliurkevych.pdp.pdpspringcore.storage;

import static com.fliurkevych.pdp.pdpspringcore.util.CacheConstants.TICKETS_CACHE_NAME;
import static com.fliurkevych.pdp.pdpspringcore.util.PageUtils.getPage;
import static com.fliurkevych.pdp.pdpspringcore.util.PageUtils.splitInPages;
import static com.fliurkevych.pdp.pdpspringcore.util.PageUtils.validatePageResult;
import static com.fliurkevych.pdp.pdpspringcore.util.SearchUtils.searchByLong;

import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import com.fliurkevych.pdp.pdpspringcore.util.CacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
public class CacheTicketStorage implements TicketStorage {

  private final Cache cache;

  @Autowired
  public CacheTicketStorage(CacheManager cacheManager) {
    this.cache = cacheManager.getCache(TICKETS_CACHE_NAME);
  }

  @Override
  public Ticket save(Ticket ticket) {
    cache.put(ticket.getId(), ticket);
    return CacheUtils.getElementByKey(cache, ticket.getId(), Ticket.class)
      .orElse(null);
  }

  @Override
  public List<Ticket> getBookedTicketsForUser(Long userId, int pageSize, int pageNum) {
    var allTickets = CacheUtils.getAllElements(cache, Ticket.class);

    var filteredEvents = searchByLong(allTickets.values(), userId, Ticket::getUserId);
    var eventPages = splitInPages(filteredEvents, pageSize);

    validatePageResult(pageNum);
    return getPage(eventPages, pageNum);
  }

  @Override
  public List<Ticket> getBookedTicketsForEvent(Long eventId, int pageSize, int pageNum) {
    var allTickets = CacheUtils.getAllElements(cache, Ticket.class);

    var filteredEvents = searchByLong(allTickets.values(), eventId, Ticket::getEventId);
    var eventPages = splitInPages(filteredEvents, pageSize);

    validatePageResult(pageNum);
    return getPage(eventPages, pageNum);
  }

  @Override
  public boolean delete(Long ticketId) {
    return cache.evictIfPresent(ticketId);
  }
}
