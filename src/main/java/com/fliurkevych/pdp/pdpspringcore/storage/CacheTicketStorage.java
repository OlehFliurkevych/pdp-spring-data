package com.fliurkevych.pdp.pdpspringcore.storage;

import static com.fliurkevych.pdp.pdpspringcore.util.CacheConstants.TICKETS_CACHE_NAME;
import static com.fliurkevych.pdp.pdpspringcore.util.PageUtils.getPage;
import static com.fliurkevych.pdp.pdpspringcore.util.PageUtils.splitInPages;
import static com.fliurkevych.pdp.pdpspringcore.util.PageUtils.validatePageResult;
import static com.fliurkevych.pdp.pdpspringcore.util.SearchUtils.searchByLong;

import com.fliurkevych.pdp.pdpspringcore.enums.TicketCategory;
import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import com.fliurkevych.pdp.pdpspringcore.util.CacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
public class CacheTicketStorage implements TicketStorage {

  private static final Random random = new Random();

  private final Cache cache;

  @Autowired
  public CacheTicketStorage(CacheManager cacheManager) {
    this.cache = cacheManager.getCache(TICKETS_CACHE_NAME);
  }

  @PostConstruct
  private void populateCache() {
    if (cache != null) {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 100; j++) {
          var userId = (long) random.nextInt(1000);
          var eventId = (long) random.nextInt(500);
          var category = TicketCategory.values()[random.nextInt(3)];
          var ticketId = (long) i + j;
          var ticket = new Ticket(ticketId, eventId, userId, j, category);

          cache.put(ticketId, ticket);
          log.info("Added new record to [tickets] cache with key: [{}]", ticketId);
        }
      }
    }
  }

  @Override
  public Ticket save(Ticket ticket) {
    return (Ticket) cache.putIfAbsent(ticket.getId(), ticket).get();
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
