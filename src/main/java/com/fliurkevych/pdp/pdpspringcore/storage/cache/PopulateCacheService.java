package com.fliurkevych.pdp.pdpspringcore.storage.cache;

import static com.fliurkevych.pdp.pdpspringcore.util.CacheConstants.EVENTS_CACHE_NAME;
import static com.fliurkevych.pdp.pdpspringcore.util.CacheConstants.USERS_CACHE_NAME;
import static com.fliurkevych.pdp.pdpspringcore.util.CacheConstants.USER_ACCOUNTS_CACHE_NAME;

import com.fliurkevych.pdp.pdpspringcore.model.Event;
import com.fliurkevych.pdp.pdpspringcore.model.User;
import com.fliurkevych.pdp.pdpspringcore.model.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Random;
import javax.annotation.PostConstruct;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Component
@DependsOn({"eventStorage", "userStorage", "ticketStorage"})
@ConditionalOnProperty(prefix = "storage", name = "type", havingValue = "CACHE")
public class PopulateCacheService {

  private final Random random;
  private final Cache eventCache;
  private final Cache userCache;
  private final Cache userAccountCache;

  public PopulateCacheService(CacheManager cacheManager) {
    this.random = new Random();
    this.eventCache = cacheManager.getCache(EVENTS_CACHE_NAME);
    this.userCache = cacheManager.getCache(USERS_CACHE_NAME);
    this.userAccountCache = cacheManager.getCache(USER_ACCOUNTS_CACHE_NAME);
  }

  @PostConstruct
  public void populateCache() {
    if (eventCache != null && userCache != null && userAccountCache != null) {
      log.info("Populating cache for events");
      for (int i = 0; i < 500; i++) {
        var event = new Event((long) i, "Event title #" + i,
          Date.from(Instant.now().plus(Duration.ofDays(random.nextInt(15) * 365L))),
          BigDecimal.valueOf(random.nextInt(100)));
        var eventId = event.getId();
        eventCache.put(eventId, event);
        log.info("Added new [Event] record into cache with id [{}]", eventId);
      }
      log.info("Populating cache for users and user accounts");
      for (int i = 0; i < 150; i++) {
        var key = (long) i;
        var user = new User(key, "User" + i, "user_" + i + "@gmail.com");

        var userAccount = new UserAccount((long) i, BigDecimal.valueOf(random.nextInt(200)));
        user.setUserAccount(userAccount);

        userCache.put(key, user);
        log.info("Added new [User] record into cache with id [{}]", key);
        userAccountCache.put(key, userAccount);
        log.info("Added new [User Account] record into cache with id [{}]", key);
      }
    }
  }

}
