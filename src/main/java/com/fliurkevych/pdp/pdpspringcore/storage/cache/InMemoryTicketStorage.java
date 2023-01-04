package com.fliurkevych.pdp.pdpspringcore.storage.cache;

import static com.fliurkevych.pdp.pdpspringcore.util.CacheConstants.TICKETS_CACHE_NAME;
import static com.fliurkevych.pdp.pdpspringcore.util.PageUtils.getPage;
import static com.fliurkevych.pdp.pdpspringcore.util.PageUtils.splitInPages;
import static com.fliurkevych.pdp.pdpspringcore.util.PageUtils.validatePageResult;
import static com.fliurkevych.pdp.pdpspringcore.util.SearchUtils.searchByLong;

import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import com.fliurkevych.pdp.pdpspringcore.storage.TicketStorage;
import com.fliurkevych.pdp.pdpspringcore.util.CacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
public class InMemoryTicketStorage implements TicketStorage {

  private final Cache cache;

  public InMemoryTicketStorage(CacheManager cacheManager) {
    this.cache = cacheManager.getCache(TICKETS_CACHE_NAME);
  }

  @Override
  public Ticket save(Ticket ticket) {
    var userId = ticket.getUser().getId();
    var eventId = ticket.getEvent().getId();
    var ticketId = Long.valueOf(StringUtils.join(userId, eventId));
    cache.put(ticketId, ticket);
    return CacheUtils.getElementByKey(cache, ticketId, Ticket.class)
      .orElse(null);
  }

  @Override
  public List<Ticket> getBookedTicketsForUser(Long userId, Pageable pageable) {
    final var pageSize = pageable.getPageSize();
    final var pageNum = pageable.getPageNumber();
    var allTickets = CacheUtils.getAllElements(cache, Ticket.class);

    var filteredEvents = searchByLong(allTickets.values(), userId, t -> t.getUser().getId());
    var eventPages = splitInPages(filteredEvents, pageSize);

    validatePageResult(pageNum);
    return getPage(eventPages, pageNum);
  }

  @Override
  public List<Ticket> getBookedTicketsForEvent(Long eventId, Pageable pageable) {
    final var pageNum = pageable.getPageNumber();
    final var pageSize = pageable.getPageSize();
    var allTickets = CacheUtils.getAllElements(cache, Ticket.class);

    var filteredEvents = searchByLong(allTickets.values(), eventId, t -> t.getEvent().getId());
    var eventPages = splitInPages(filteredEvents, pageSize);

    validatePageResult(pageNum);
    return getPage(eventPages, pageNum);
  }

  @Override
  public boolean delete(Long ticketId) {
    return cache.evictIfPresent(ticketId);
  }
}
