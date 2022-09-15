package com.fliurkevych.pdp.pdpspringcore.util;

import com.fliurkevych.pdp.pdpspringcore.exception.ValidationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Oleh Fliurkevych
 */
public final class PageUtils {

  public static <T> List<List<T>> splitInPages(Collection<T> filteredEvents, int pageSize) {
    final AtomicInteger counter = new AtomicInteger();
    return new ArrayList<>(filteredEvents.stream()
      .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / pageSize))
      .values());
  }

  public static void validatePageResult(int pageNumber) {
    if (pageNumber < 0) {
      throw new ValidationException("Passed incorrect page number");
    }
  }

  public static  <T> List<T> getPage(List<List<T>> pages, int pageNum) {
    if (pages.size() >= pageNum) {
      return pages.get(pageNum - 1);
    } else {
      return Collections.emptyList();
    }
  }

}
