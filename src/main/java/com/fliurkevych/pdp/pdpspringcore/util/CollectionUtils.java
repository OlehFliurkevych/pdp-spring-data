package com.fliurkevych.pdp.pdpspringcore.util;

import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

public class CollectionUtils {

  /**
   * Basic folding of the specified iterable source seeded with initial value and accumulated by the
   * provided operation
   */
  public static <T, R> R fold(final Iterable<T> source,
                              final R initial,
                              final BiFunction<R, T, R> operation) {
    var accumulator = initial;

    for (T element : source) {
      accumulator = operation.apply(accumulator, element);
    }

    return accumulator;
  }

  /**
   * Partition list into list of chunks of specified size
   */
  public static <T> List<List<T>> partition(final List<T> source, final int chunkSize) {
    return fold(source, new ArrayList<>(), (ArrayList<List<T>> current, T item) -> {
      if (current.isEmpty() || current.get(current.size() - 1).size() % chunkSize == 0) {
        current.add(new ArrayList<>());
      }

      current.get(current.size() - 1).add(item);
      return current;
    });
  }


  /**
   * Zip specified collection into a list of pairs, where first component is represented by current
   * element and the second component is the corresponding elements index.
   * <pre>
   * Example:
   * {@code zipWithIndex(List.of("one", "two", "three"))}
   * is an equivalent to:
   * {@code List.of(Pair.of("one", 0), Pair.of("two", 1), Pair.of("three", 2)))}
   * </pre>
   */
  public static <T> List<Pair<T, Integer>> zipWithIndex(final Collection<T> source) {
    return fold(source, new ArrayList<>(), (ArrayList<Pair<T, Integer>> current, T item) -> {
      current.add(Pair.of(item, current.size()));
      return current;
    });
  }
}
