package com.fliurkevych.pdp.pdpspringcore.util;

import com.fliurkevych.pdp.pdpspringcore.exception.NotFoundException;
import com.fliurkevych.pdp.pdpspringcore.exception.NotSupportedException;
import org.springframework.cache.Cache;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Oleh Fliurkevych
 */
public final class CacheUtils {

  public static <T> ConcurrentMap<Long, T> getAllElements(Cache cache, Class<T> tClass) {
    return Optional.ofNullable(cache)
      .map(Cache::getNativeCache)
      .map(obj -> (ConcurrentMap<Long, T>) obj)
      .orElseThrow(() -> new NotSupportedException(
        String.format("Can't get all elements from cache for: [%s]", tClass.getName())));
  }

  public static <T> Optional<T> getElementByKey(Cache cache, Long key, Class<T> tClass) {
    return Optional.ofNullable(cache)
      .map(c -> c.get(key))
      .map(wrapper -> (T) wrapper.get());
    
  }

}
