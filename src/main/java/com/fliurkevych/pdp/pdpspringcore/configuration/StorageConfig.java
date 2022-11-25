package com.fliurkevych.pdp.pdpspringcore.configuration;

import com.fliurkevych.pdp.pdpspringcore.storage.EventStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.TicketStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.UserStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.cache.CacheEventStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.cache.CacheTicketStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.cache.CacheUserStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.EventRepository;
import com.fliurkevych.pdp.pdpspringcore.storage.db.TicketRepository;
import com.fliurkevych.pdp.pdpspringcore.storage.db.UserRepository;
import com.fliurkevych.pdp.pdpspringcore.storage.db.impl.DbEventStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.impl.DbTicketStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.impl.DbUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
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
  public EventStorage eventStorage(EventRepository eventRepository) {
    return new DbEventStorage(eventRepository);
  }

  @Bean
  public UserStorage userStorage(UserRepository userRepository) {
    return new DbUserStorage(userRepository);
  }

  @Bean
  public TicketStorage ticketStorage(TicketRepository ticketRepository) {
    return new DbTicketStorage(ticketRepository);
  }
}
