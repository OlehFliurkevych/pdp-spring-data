package com.fliurkevych.pdp.pdpspringcore.configuration;

import com.fliurkevych.pdp.pdpspringcore.storage.EventStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.TicketStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.UserStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.DbEventRepository;
import com.fliurkevych.pdp.pdpspringcore.storage.db.DbTicketRepository;
import com.fliurkevych.pdp.pdpspringcore.storage.db.DbUserRepository;
import com.fliurkevych.pdp.pdpspringcore.storage.db.impl.DbEventStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.impl.DbTicketStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.impl.DbUserStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

//  @Bean
//  public EventStorage eventStorage(CacheManager manager) {
//    return new CacheEventStorage(manager);
//  }
//
//  @Bean
//  public UserStorage userStorage(CacheManager manager) {
//    return new CacheUserStorage(manager);
//  }
//
//  @Bean
//  public TicketStorage ticketStorage(CacheManager manager) {
//    return new CacheTicketStorage(manager);
//  }

  @Bean
  public EventStorage eventStorage(DbEventRepository dbEventRepository) {
    return new DbEventStorage(dbEventRepository);
  }

  @Bean
  public UserStorage userStorage(DbUserRepository dbUserRepository) {
    return new DbUserStorage(dbUserRepository);
  }

  @Bean
  public TicketStorage ticketStorage(DbTicketRepository dbTicketRepository) {
    return new DbTicketStorage(dbTicketRepository);
  }
}
