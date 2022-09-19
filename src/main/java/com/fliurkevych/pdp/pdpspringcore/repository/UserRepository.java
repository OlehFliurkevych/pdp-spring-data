package com.fliurkevych.pdp.pdpspringcore.repository;

import static com.fliurkevych.pdp.pdpspringcore.util.CacheConstants.USERS_CACHE_NAME;
import static com.fliurkevych.pdp.pdpspringcore.util.PageUtils.getPage;
import static com.fliurkevych.pdp.pdpspringcore.util.PageUtils.splitInPages;
import static com.fliurkevych.pdp.pdpspringcore.util.PageUtils.validatePageResult;
import static com.fliurkevych.pdp.pdpspringcore.util.SearchUtils.searchByText;

import com.fliurkevych.pdp.pdpspringcore.model.User;
import com.fliurkevych.pdp.pdpspringcore.util.CacheConstants;
import com.fliurkevych.pdp.pdpspringcore.util.CacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

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
public class UserRepository {

  private static final Random random = new Random();
  @Autowired
  private CacheManager cacheManager;

  @PostConstruct
  private void populateCache() {
    var cache = cacheManager.getCache(USERS_CACHE_NAME);
    if (Objects.nonNull(cache)) {
      for (int i = 0; i < 1000; i++) {
        var user = new User((long) i, "User" + i, "user_" + i + "@gmail.com");
        var userId = user.getId();
        cache.put(user.getId(), user);
        log.info("Added new record to [users] cache with key: [{}]", userId);
      }
    }
  }

  public Optional<User> getUserById(Long userId) {
    return CacheUtils.getElementByKey(cacheManager.getCache(USERS_CACHE_NAME),
      userId, User.class);
  }

  public Optional<User> getUserByEmail(String email) {
    var allUsers = CacheUtils.getAllElements(
      cacheManager.getCache(USERS_CACHE_NAME), User.class);

    var filteredUser = searchByText(allUsers.values(), email, User::getEmail);

    return filteredUser.stream()
      .findFirst();
  }

  public List<User> getUsersByName(String name, int pageSize, int pageNum) {
    var allUsers = CacheUtils.getAllElements(
      cacheManager.getCache(USERS_CACHE_NAME), User.class);

    var filteredEvent = searchByText(allUsers.values(), name, User::getName);
    var eventPages = splitInPages(filteredEvent, pageSize);

    validatePageResult(pageNum);
    return getPage(eventPages, pageNum);
  }

  public User save(User user) {
    return (User) cacheManager.getCache(USERS_CACHE_NAME)
      .putIfAbsent(user.getId(), user).get();
  }

  public User update(User user) {
    cacheManager.getCache(USERS_CACHE_NAME).evictIfPresent(user.getId());
    return (User) cacheManager.getCache(CacheConstants.EVENTS_CACHE_NAME)
      .putIfAbsent(user.getId(), user).get();
  }

  public boolean delete(Long userId) {
    return cacheManager.getCache(USERS_CACHE_NAME).evictIfPresent(userId);
  }

}
