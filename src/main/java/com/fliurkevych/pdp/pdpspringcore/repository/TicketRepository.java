package com.fliurkevych.pdp.pdpspringcore.repository;

import static com.fliurkevych.pdp.pdpspringcore.util.CacheConstants.USERS_CACHE_NAME;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Random;
import javax.annotation.PostConstruct;

/**
 * @author Oleh Fliurkevych
 */
//@Repository
@Component
public class TicketRepository {

  private static final Random random = new Random();
  @Autowired
  private CacheManager cacheManager;

  @PostConstruct
  private void populateCache() {
    var cache = cacheManager.getCache(USERS_CACHE_NAME);
    if (Objects.nonNull(cache)) {
      // TODO: 9/5/2022 Populate ticket cache 
    }
  }

}
