package com.fliurkevych.pdp.pdpspringcore.service;

import static com.fliurkevych.pdp.pdpspringcore.util.EventTestUtils.DATE_1;
import static com.fliurkevych.pdp.pdpspringcore.util.EventTestUtils.EVENT_1;
import static com.fliurkevych.pdp.pdpspringcore.util.EventTestUtils.EVENT_2;
import static com.fliurkevych.pdp.pdpspringcore.util.EventTestUtils.TITLE_1;
import static com.fliurkevych.pdp.pdpspringcore.util.EventTestUtils.TITLE_2;
import static org.mockito.Mockito.when;

import com.fliurkevych.pdp.pdpspringcore.exception.NotFoundException;
import com.fliurkevych.pdp.pdpspringcore.exception.ValidationException;
import com.fliurkevych.pdp.pdpspringcore.model.Event;
import com.fliurkevych.pdp.pdpspringcore.storage.EventStorage;
import com.fliurkevych.pdp.pdpspringcore.util.EventTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author Oleh Fliurkevych
 */
@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

  private static final Pageable PAGEABLE = PageRequest.of(1, 10);

  @Mock
  private EventStorage eventStorage;
  @InjectMocks
  private EventService eventService;

  @Test
  public void getEventById() {
    var event = EventTestUtils.buildEvent(EVENT_1, TITLE_1, DATE_1);

    when(eventStorage.getEventById(EVENT_1)).thenReturn(Optional.of(event));

    var result = eventService.getEventById(EVENT_1);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(EVENT_1, result.getId());
  }

  @Test
  public void getEventByIdShouldThrowNotFoundExceptionTest() {
    when(eventStorage.getEventById(EVENT_1)).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> eventService.getEventById(EVENT_1));
  }

  @Test
  public void getEventsByTitleTest() {
    var event1 = EventTestUtils.buildEvent(EVENT_1, TITLE_1, DATE_1);
    var event2 = EventTestUtils.buildEvent(EVENT_2, TITLE_2, DATE_1);

    when(eventStorage.getEventsByTitle("title 11", PAGEABLE)).thenReturn(
      List.of(event1, event2));

    var result = eventService.getEventsByTitle("title 11", PAGEABLE);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(2, result.size());
  }

  @Test
  public void getEventsByDateTest() {
    var event1 = EventTestUtils.buildEvent(EVENT_1, TITLE_1, DATE_1);
    var event2 = EventTestUtils.buildEvent(EVENT_2, TITLE_2, DATE_1);

    when(eventStorage.getEventsForDay(DATE_1, PAGEABLE)).thenReturn(
      List.of(event1, event2));

    var result = eventService.getEventsByDate(DATE_1, PAGEABLE);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(2, result.size());
  }

  @Test
  public void createUserTest() {
    var event = EventTestUtils.buildEvent(EVENT_1, TITLE_1, DATE_1);
    var eventDto = EventTestUtils.buildEventDto(EVENT_1, TITLE_1, DATE_1);

    when(eventStorage.exists(EVENT_1)).thenReturn(false);
    when(eventStorage.save(ArgumentMatchers.any(Event.class))).thenReturn(event);

    var result = eventService.create(eventDto);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(EVENT_1, result.getId());
  }

  @Test
  public void createEventShouldThrowValidationTest() {
    var eventDto = EventTestUtils.buildEventDto(EVENT_1, TITLE_1, DATE_1);
    when(eventStorage.exists(EVENT_1)).thenReturn(true);

    Assertions.assertThrows(ValidationException.class, () -> eventService.create(eventDto));
  }

  @Test
  public void updateEventTest() {
    var eventDto = EventTestUtils.buildEventDto(EVENT_1, TITLE_2, DATE_1);
    var eventBefore = EventTestUtils.buildEvent(EVENT_1, TITLE_1, DATE_1);
    var eventAfter = EventTestUtils.buildEvent(EVENT_1, TITLE_2, DATE_1);

    when(eventStorage.getEventById(EVENT_1)).thenReturn(Optional.of(eventBefore));
    when(eventStorage.update(ArgumentMatchers.any(Event.class))).thenReturn(eventAfter);

    var result = eventService.update(eventDto);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(EVENT_1, result.getId());
    Assertions.assertEquals(TITLE_2, result.getTitle());
  }

  @Test
  public void updateEventShouldThrowNotFoundTest() {
    var eventDto = EventTestUtils.buildEventDto(EVENT_1, TITLE_1, DATE_1);
    when(eventStorage.getEventById(EVENT_1)).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> eventService.update(eventDto));
  }

  @Test
  public void deleteEventTest() {
    var event = EventTestUtils.buildEvent(EVENT_1, TITLE_1, DATE_1);

    when(eventStorage.getEventById(EVENT_1)).thenReturn(Optional.of(event));
    when(eventStorage.delete(EVENT_1)).thenReturn(true);

    var result = eventService.delete(EVENT_1);
    Assertions.assertTrue(result);
  }

  @Test
  public void deleteEventShouldThrowNotFoundTest() {
    when(eventStorage.getEventById(EVENT_1)).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> eventService.delete(EVENT_1));
  }

}
