package com.fliurkevych.pdp.pdpspringcore.repository;

import static com.fliurkevych.pdp.pdpspringcore.util.CacheConstants.TICKETS_CACHE_NAME;

import com.fliurkevych.pdp.pdpspringcore.enums.TicketCategory;
import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Random;
import javax.annotation.PostConstruct;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Component
public class TicketRepository {

  private static final Random random = new Random();
  @Autowired
  private CacheManager cacheManager;

  @PostConstruct
  private void populateCache() {
    var cache = cacheManager.getCache(TICKETS_CACHE_NAME);
    if (Objects.nonNull(cache)) {
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

}
