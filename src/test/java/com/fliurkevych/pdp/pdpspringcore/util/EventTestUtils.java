package com.fliurkevych.pdp.pdpspringcore.util;

import com.fliurkevych.pdp.pdpspringcore.dto.EventDto;
import com.fliurkevych.pdp.pdpspringcore.model.Event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

/**
 * @author Oleh Fliurkevych
 */
public final class EventTestUtils {

  public static final Long EVENT_1 = 111L;
  public static final Long EVENT_2 = 222L;
  public static final String TITLE_1 = "title 111";
  public static final String TITLE_2 = "title 112";
  public static final Date DATE_1 = Date.from(Instant.now());

  public static Event buildEvent(Long id, String title, Date date) {
    return new Event(id, title, date, BigDecimal.valueOf(100L));
  }

  public static EventDto buildEventDto(Long id, String title, Date date) {
    return new EventDto(id, title, date, BigDecimal.valueOf(100L));
  }

}
