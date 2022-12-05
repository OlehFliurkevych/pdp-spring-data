package com.fliurkevych.pdp.pdpspringcore.configuration;

import com.fliurkevych.pdp.pdpspringcore.enums.StorageType;
import com.fliurkevych.pdp.pdpspringcore.storage.EventStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.TicketStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.UserAccountStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.UserStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.cache.CacheEventStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.cache.CacheTicketStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.cache.CacheUserAccountStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.cache.CacheUserStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.EventRepository;
import com.fliurkevych.pdp.pdpspringcore.storage.db.TicketRepository;
import com.fliurkevych.pdp.pdpspringcore.storage.db.UserAccountRepository;
import com.fliurkevych.pdp.pdpspringcore.storage.db.UserRepository;
import com.fliurkevych.pdp.pdpspringcore.storage.db.impl.DbEventStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.impl.DbTicketStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.impl.DbUserAccountStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.impl.DbUserStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class StorageConfig {

  @Value("${storage.type:DB}")
  private StorageType STORAGE_TYPE;

  private CacheManager cacheManager;

  @Bean
  public EventStorage eventStorage(EventRepository eventRepository) {
    switch (STORAGE_TYPE) {
      case CACHE:
        return new CacheEventStorage(cacheManager);
      default:
        return new DbEventStorage(eventRepository);
    }
  }

  @Bean
  public UserStorage userStorage(UserRepository userRepository) {
    switch (STORAGE_TYPE) {
      case CACHE:
        return new CacheUserStorage(cacheManager);
      default:
        return new DbUserStorage(userRepository);
    }
  }

  @Bean
  public TicketStorage ticketStorage(TicketRepository ticketRepository) {
    switch (STORAGE_TYPE) {
      case CACHE:
        return new CacheTicketStorage(cacheManager);
      default:
        return new DbTicketStorage(ticketRepository);
    }
  }

  @Bean
  public UserAccountStorage userAccountStorage(UserAccountRepository userAccountRepository) {
    switch (STORAGE_TYPE) {
      case CACHE:
        return new CacheUserAccountStorage(cacheManager);
      default:
        return new DbUserAccountStorage(userAccountRepository);
    }
  }
}
