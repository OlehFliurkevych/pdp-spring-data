package com.fliurkevych.pdp.pdpspringcore.configuration;

import com.fliurkevych.pdp.pdpspringcore.storage.CacheEventStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.CacheTicketStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.CacheUserStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.EventStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.TicketStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.UserStorage;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

  @Bean
  public EventStorage eventStorage(CacheManager manager) {
    return new CacheEventStorage(manager);
  }

  @Bean
  public UserStorage userStorage(CacheManager manager) {
    return new CacheUserStorage(manager);
  }

  @Bean
  public TicketStorage ticketStorage(CacheManager manager) {
    return new CacheTicketStorage(manager);
  }
}
