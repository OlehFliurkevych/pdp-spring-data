package com.fliurkevych.pdp.pdpspringcore.configuration;

import com.fliurkevych.pdp.pdpspringcore.enums.StorageType;
import com.fliurkevych.pdp.pdpspringcore.storage.EventStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.TicketStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.UserAccountStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.UserStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.cache.InMemoryEventStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.cache.InMemoryTicketStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.cache.InMemoryUserAccountStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.cache.InMemoryUserStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.EventRepository;
import com.fliurkevych.pdp.pdpspringcore.storage.db.TicketRepository;
import com.fliurkevych.pdp.pdpspringcore.storage.db.UserAccountRepository;
import com.fliurkevych.pdp.pdpspringcore.storage.db.UserRepository;
import com.fliurkevych.pdp.pdpspringcore.storage.db.impl.DbEventStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.impl.DbTicketStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.impl.DbUserAccountStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.impl.DbUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class StorageConfig {

  @Value("${storage.type:DB}")
  private StorageType STORAGE_TYPE;

  @Autowired
  private CacheManager cacheManager;

  @Bean
  @ConditionalOnProperty(prefix = "storage", name = "type", havingValue = "DB")
  public EventStorage dbEventStorage(EventRepository eventRepository) {
    return new DbEventStorage(eventRepository);
  }

  @Bean
  @ConditionalOnProperty(prefix = "storage", name = "type", havingValue = "MEMORY")
  public EventStorage memoryEventStorage() {
    return new InMemoryEventStorage(cacheManager);
  }

  @Bean
  @ConditionalOnProperty(prefix = "storage", name = "type", havingValue = "DB")
  public UserStorage dbUserStorage(UserRepository userRepository) {
    return new DbUserStorage(userRepository);
  }

  @Bean
  @ConditionalOnProperty(prefix = "storage", name = "type", havingValue = "MEMORY")
  public UserStorage memoryUserStorage() {
    return new InMemoryUserStorage(cacheManager);
  }

  @Bean
  @ConditionalOnProperty(prefix = "storage", name = "type", havingValue = "DB")
  public TicketStorage dbTicketStorage(TicketRepository ticketRepository) {
    return new DbTicketStorage(ticketRepository);
  }

  @Bean
  @ConditionalOnProperty(prefix = "storage", name = "type", havingValue = "MEMORY")
  public TicketStorage memoryTicketStorage() {
    return new InMemoryTicketStorage(cacheManager);
  }

  @Bean
  @ConditionalOnProperty(prefix = "storage", name = "type", havingValue = "DB")
  public UserAccountStorage dbUserAccountStorage(UserAccountRepository userAccountRepository) {
    return new DbUserAccountStorage(userAccountRepository);
  }

  @Bean
  @ConditionalOnProperty(prefix = "storage", name = "type", havingValue = "MEMORY")
  public UserAccountStorage memoryUserAccountStorage() {
    return new InMemoryUserAccountStorage(cacheManager);
  }
}
