package com.fliurkevych.pdp.pdpspringcore.storage.cache;

import static com.fliurkevych.pdp.pdpspringcore.util.CacheConstants.USERS_CACHE_NAME;
import static com.fliurkevych.pdp.pdpspringcore.util.PageUtils.getPage;
import static com.fliurkevych.pdp.pdpspringcore.util.PageUtils.splitInPages;
import static com.fliurkevych.pdp.pdpspringcore.util.PageUtils.validatePageResult;
import static com.fliurkevych.pdp.pdpspringcore.util.SearchUtils.searchByText;

import com.fliurkevych.pdp.pdpspringcore.model.User;
import com.fliurkevych.pdp.pdpspringcore.storage.UserStorage;
import com.fliurkevych.pdp.pdpspringcore.util.CacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
public class CacheUserStorage implements UserStorage {

  private final Cache cache;

  public CacheUserStorage(CacheManager cacheManager) {
    this.cache = cacheManager.getCache(USERS_CACHE_NAME);
  }

  @PostConstruct
  private void populateCache() {
    if (cache != null) {
      for (int i = 0; i < 1000; i++) {
//        var user = new User((long) i, "User" + i, "user_" + i + "@gmail.com");
//        var userId = user.getId();
//        cache.put(user.getId(), user);
//        log.info("Added new record to [users] cache with key: [{}]", userId);
      }
    }
  }

  @Override
  public Optional<User> getUserById(Long userId) {
    return CacheUtils.getElementByKey(cache, userId, User.class);
  }

  @Override
  public Optional<User> getUserByEmail(String email) {
    var allUsers = CacheUtils.getAllElements(cache, User.class);

    var filteredUser = searchByText(allUsers.values(), email, User::getEmail);

    return filteredUser.stream()
      .findFirst();
  }

  @Override
  public List<User> getUsersByName(String name, Pageable pageable) {
    final var pageNum = pageable.getPageNumber();
    final var pageSize = pageable.getPageSize();
    var allUsers = CacheUtils.getAllElements(cache, User.class);

    var filteredEvent = searchByText(allUsers.values(), name, User::getName);
    var eventPages = splitInPages(filteredEvent, pageSize);

    validatePageResult(pageNum);
    return getPage(eventPages, pageNum);
  }

  @Override
  public User save(User user) {
    return (User) cache.putIfAbsent(user.getId(), user).get();
  }

  @Override
  public User update(User user) {
    cache.evictIfPresent(user.getId());
    return (User) cache.putIfAbsent(user.getId(), user).get();
  }

  @Override
  public boolean delete(Long userId) {
    return cache.evictIfPresent(userId);
  }

  @Override
  public Collection<User> getAllUsers() {
    return CacheUtils.getAllElements(cache, User.class).values();
  }

}
