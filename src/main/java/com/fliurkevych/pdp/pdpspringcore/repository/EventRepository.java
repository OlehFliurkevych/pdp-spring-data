package com.fliurkevych.pdp.pdpspringcore.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Objects;
import javax.annotation.PostConstruct;

/**
 * @author Oleh Fliurkevych
 */
//@Repository
@Component
public class EventRepository {

  @Autowired
  private CacheManager cacheManager;

  @PostConstruct
  public void populateCache() {
    var cache = cacheManager.getCache("events");
    if (Objects.isNull(cache)) {
      // TODO: 9/5/2022 Populate event cache 
    }
  }

}
