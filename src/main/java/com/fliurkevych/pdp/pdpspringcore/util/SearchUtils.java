package com.fliurkevych.pdp.pdpspringcore.util;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Oleh Fliurkevych
 */
public final class SearchUtils {

  public static <T> Collection<T> searchByText(Collection<T> all, String searchText,
    Function<T, String> function) {
    return all.stream()
      .filter(element -> function.apply(element).contains(searchText))
      .collect(Collectors.toList());
  }

  public static <T> Collection<T> searchByDate(Collection<T> all, Date searchDate,
    Function<T, Date> function) {
    return all.stream()
      .filter(element -> function.apply(element).compareTo(searchDate) == 0)
      .collect(Collectors.toList());
  }

  public static <T> Collection<T> searchByLong(Collection<T> all, Long searchLong,
    Function<T, Long> function) {
    return all.stream()
      .filter(element -> Objects.equals(function.apply(element), searchLong))
      .collect(Collectors.toList());
  }

}
