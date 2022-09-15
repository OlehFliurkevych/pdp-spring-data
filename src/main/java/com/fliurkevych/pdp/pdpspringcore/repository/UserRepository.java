package com.fliurkevych.pdp.pdpspringcore.repository;

import static com.fliurkevych.pdp.pdpspringcore.util.CacheConstants.USERS_CACHE_NAME;

import com.fliurkevych.pdp.pdpspringcore.model.User;
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

  public User getById(Long userId) {
    return cacheManager.getCache(USERS_CACHE_NAME).get(userId, User.class);
  }

}
