package com.fliurkevych.pdp.pdpspringcore.repository;

import static com.fliurkevych.pdp.pdpspringcore.util.PageUtils.getPage;
import static com.fliurkevych.pdp.pdpspringcore.util.PageUtils.splitInPages;
import static com.fliurkevych.pdp.pdpspringcore.util.PageUtils.validatePageResult;
import static com.fliurkevych.pdp.pdpspringcore.util.SearchUtils.searchByDate;
import static com.fliurkevych.pdp.pdpspringcore.util.SearchUtils.searchByText;

import com.fliurkevych.pdp.pdpspringcore.model.Event;
import com.fliurkevych.pdp.pdpspringcore.util.CacheConstants;
import com.fliurkevych.pdp.pdpspringcore.util.CacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import javax.annotation.PostConstruct;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Component
public class EventRepository {

  private static final Random random = new Random();
  @Autowired
  private CacheManager cacheManager;

  @PostConstruct
  public void populateCache() {
    var cache = cacheManager.getCache(CacheConstants.EVENTS_CACHE_NAME);
    if (Objects.nonNull(cache)) {
      for (int i = 0; i < 500; i++) {
        var event = new Event((long) i, "Event title #" + i,
          Date.from(Instant.now().plus(Duration.ofDays(random.nextInt(15) * 365L))));
        var eventId = event.getId();
        cache.put(eventId, event);
        log.info("Added new record to [events] cache with key: [{}]", eventId);
      }
    }
  }

  public Optional<Event> getEventById(Long eventId) {
    return CacheUtils.getElementByKey(cacheManager.getCache(CacheConstants.EVENTS_CACHE_NAME),
      eventId, Event.class);
  }

  public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
    var allEvents = CacheUtils.getAllElements(
      cacheManager.getCache(CacheConstants.EVENTS_CACHE_NAME), Event.class);

    var filteredEvent = searchByText(allEvents.values(), title, Event::getTitle);
    var eventPages = splitInPages(filteredEvent, pageSize);

    validatePageResult(pageNum);
    return getPage(eventPages, pageNum);
  }

  public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
    var allEvents = CacheUtils.getAllElements(
      cacheManager.getCache(CacheConstants.EVENTS_CACHE_NAME), Event.class);

    var filteredEvents = searchByDate(allEvents.values(), day, Event::getDate);
    var eventPages = splitInPages(filteredEvents, pageSize);

    validatePageResult(pageNum);
    return getPage(eventPages, pageNum);
  }

  public Event save(Event event) {
    return (Event) cacheManager.getCache(CacheConstants.EVENTS_CACHE_NAME)
      .putIfAbsent(event.getId(), event).get();
  }

  public Event update(Event event) {
    cacheManager.getCache(CacheConstants.EVENTS_CACHE_NAME).evictIfPresent(event.getId());
    return (Event) cacheManager.getCache(CacheConstants.EVENTS_CACHE_NAME)
      .putIfAbsent(event.getId(), event).get();
  }

  public boolean delete(Long eventId) {
    return cacheManager.getCache(CacheConstants.EVENTS_CACHE_NAME).evictIfPresent(eventId);
  }


}
