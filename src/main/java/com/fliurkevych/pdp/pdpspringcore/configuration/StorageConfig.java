package com.fliurkevych.pdp.pdpspringcore.configuration;

import com.fliurkevych.pdp.pdpspringcore.storage.CacheEventStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.EventStorage;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

  @Bean
  public EventStorage eventStorage(CacheManager manager) {
    return new CacheEventStorage(manager);
  }
}
