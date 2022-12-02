package com.fliurkevych.pdp.pdpspringcore.storage.cache;

import static com.fliurkevych.pdp.pdpspringcore.util.CacheConstants.USER_ACCOUNTS_CACHE_NAME;
import static com.fliurkevych.pdp.pdpspringcore.util.SearchUtils.searchByLong;

import com.fliurkevych.pdp.pdpspringcore.model.UserAccount;
import com.fliurkevych.pdp.pdpspringcore.storage.UserAccountStorage;
import com.fliurkevych.pdp.pdpspringcore.util.CacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Optional;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
public class CacheUserAccountStorage implements UserAccountStorage {

  private final Cache cache;

  public CacheUserAccountStorage(CacheManager cacheManager) {
    this.cache = cacheManager.getCache(USER_ACCOUNTS_CACHE_NAME);
  }

  @Override
  public Optional<UserAccount> getUserAccountByUserId(Long userId) {
    var allUserAccounts = CacheUtils.getAllElements(cache, UserAccount.class);

    var filteredUser = searchByLong(allUserAccounts.values(), userId, ua -> ua.getUser().getId());

    return filteredUser.stream()
      .findFirst();
  }

  @Override
  public UserAccount update(UserAccount userAccount) {
    cache.evictIfPresent(userAccount.getId());
    return (UserAccount) cache.putIfAbsent(userAccount.getId(), userAccount).get();
  }

}
