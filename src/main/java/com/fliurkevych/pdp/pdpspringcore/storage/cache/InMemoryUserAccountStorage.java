package com.fliurkevych.pdp.pdpspringcore.storage.cache;

import static com.fliurkevych.pdp.pdpspringcore.util.CacheConstants.USER_ACCOUNTS_CACHE_NAME;

import com.fliurkevych.pdp.pdpspringcore.model.UserAccount;
import com.fliurkevych.pdp.pdpspringcore.storage.UserAccountStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
public class InMemoryUserAccountStorage implements UserAccountStorage {

  private final Cache cache;

  public InMemoryUserAccountStorage(CacheManager cacheManager) {
    this.cache = cacheManager.getCache(USER_ACCOUNTS_CACHE_NAME);
  }

  @Override
  public UserAccount update(UserAccount userAccount) {
    cache.evictIfPresent(userAccount.getId());
    cache.put(userAccount.getId(), userAccount);
    return cache.get(userAccount.getId(), UserAccount.class);
  }

  @Override
  public UserAccount save(UserAccount userAccount) {
    return (UserAccount) cache.putIfAbsent(userAccount.getId(), userAccount).get();
  }
}
