package com.fliurkevych.pdp.pdpspringcore.storage;

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
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.annotation.PostConstruct;

@Slf4j
public class CacheEventStorage implements EventStorage {

  private static final Random random = new Random();

  private final Cache cache;

  // TODO: since you are creating this event storage via the configuration, no need to declare constructor as autowired
  // this annotation will be skipped, since this class is not marked as @Component or @Service, etc...
  @Autowired
  public CacheEventStorage(CacheManager cacheManager) {
    this.cache = cacheManager.getCache(CacheConstants.EVENTS_CACHE_NAME);
  }

  @PostConstruct
  public void populateCache() {
    if (cache != null) {
      for (int i = 0; i < 500; i++) {
        var event = new Event((long) i, "Event title #" + i,
          Date.from(Instant.now().plus(Duration.ofDays(random.nextInt(15) * 365L))));
        var eventId = event.getId();
        cache.put(eventId, event);
      }
    }
  }

  @Override
  public Optional<Event> getEventById(Long eventId) {
    return CacheUtils.getElementByKey(cache, eventId, Event.class);
  }

  @Override
  public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
    var allEvents = CacheUtils.getAllElements(cache, Event.class);

    var filteredEvent = searchByText(allEvents.values(), title, Event::getTitle);
    var eventPages = splitInPages(filteredEvent, pageSize);

    validatePageResult(pageNum);
    return getPage(eventPages, pageNum);
  }

  @Override
  public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
    var allEvents = CacheUtils.getAllElements(cache, Event.class);

    var filteredEvents = searchByDate(allEvents.values(), day, Event::getDate);
    var eventPages = splitInPages(filteredEvents, pageSize);

    validatePageResult(pageNum);
    return getPage(eventPages, pageNum);
  }

  @Override
  public Event save(Event event) {
    return (Event) cache
      .putIfAbsent(event.getId(), event).get();
  }

  @Override
  public Event update(Event event) {
    cache.evictIfPresent(event.getId());
    return (Event) cache.putIfAbsent(event.getId(), event).get();
  }

  @Override
  public boolean delete(Long eventId) {
    return cache.evictIfPresent(eventId);
  }

  @Override
  public Collection<Event> getAllEvents() {
    return CacheUtils.getAllElements(cache, Event.class).values();
  }
}
