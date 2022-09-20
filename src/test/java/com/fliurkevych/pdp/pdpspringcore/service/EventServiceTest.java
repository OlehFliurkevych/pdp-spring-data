package com.fliurkevych.pdp.pdpspringcore.service;

import static com.fliurkevych.pdp.pdpspringcore.util.EventTestUtils.DATE_1;
import static com.fliurkevych.pdp.pdpspringcore.util.EventTestUtils.EVENT_1;
import static com.fliurkevych.pdp.pdpspringcore.util.EventTestUtils.EVENT_2;
import static com.fliurkevych.pdp.pdpspringcore.util.EventTestUtils.TITLE_1;
import static com.fliurkevych.pdp.pdpspringcore.util.EventTestUtils.TITLE_2;
import static org.mockito.Mockito.when;

import com.fliurkevych.pdp.pdpspringcore.exception.NotFoundException;
import com.fliurkevych.pdp.pdpspringcore.exception.ValidationException;
import com.fliurkevych.pdp.pdpspringcore.repository.EventRepository;
import com.fliurkevych.pdp.pdpspringcore.util.EventTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

/**
 * @author Oleh Fliurkevych
 */
@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

  @Mock
  private EventRepository eventRepository;
  @InjectMocks
  private EventService eventService;

  @Test
  public void getEventById() {
    var event = EventTestUtils.buildEvent(EVENT_1, TITLE_1, DATE_1);

    when(eventRepository.getEventById(EVENT_1)).thenReturn(Optional.of(event));

    var result = eventService.getEventById(EVENT_1);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(EVENT_1, result.getId());
  }

  @Test
  public void getEventByIdShouldThrowNotFoundExceptionTest() {
    when(eventRepository.getEventById(EVENT_1)).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> eventService.getEventById(EVENT_1));
  }

  @Test
  public void getEventsByTitleTest() {
    var event1 = EventTestUtils.buildEvent(EVENT_1, TITLE_1, DATE_1);
    var event2 = EventTestUtils.buildEvent(EVENT_2, TITLE_2, DATE_1);

    when(eventRepository.getEventsByTitle("title 11", 10, 1)).thenReturn(List.of(event1, event2));

    var result = eventService.getEventsByTitle("title 11", 10, 1);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(2, result.size());
  }

  @Test
  public void getEventsByDateTest() {
    var event1 = EventTestUtils.buildEvent(EVENT_1, TITLE_1, DATE_1);
    var event2 = EventTestUtils.buildEvent(EVENT_2, TITLE_2, DATE_1);

    when(eventRepository.getEventsForDay(DATE_1, 10, 1)).thenReturn(
      List.of(event1, event2));

    var result = eventService.getEventsByDate(DATE_1, 10, 1);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(2, result.size());
  }

  @Test
  public void createUserTest() {
    var event1 = EventTestUtils.buildEvent(EVENT_1, TITLE_1, DATE_1);

    when(eventRepository.getEventById(EVENT_1)).thenReturn(Optional.empty());
    when(eventRepository.save(event1)).thenReturn(event1);

    var result = eventService.createEvent(event1);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(EVENT_1, result.getId());
  }

  @Test
  public void createEventShouldThrowValidationTest() {
    var event = EventTestUtils.buildEvent(EVENT_1, TITLE_1, DATE_1);
    when(eventRepository.getEventById(EVENT_1)).thenReturn(Optional.of(event));

    Assertions.assertThrows(ValidationException.class, () -> eventService.createEvent(event));
  }

  @Test
  public void updateEventTest() {
    var event = EventTestUtils.buildEvent(EVENT_1, TITLE_1, DATE_1);
    var eventUpdate = EventTestUtils.buildEvent(EVENT_1, TITLE_2, DATE_1);

    when(eventRepository.getEventById(EVENT_1)).thenReturn(Optional.of(event));
    when(eventRepository.update(eventUpdate)).thenReturn(eventUpdate);

    var result = eventService.updateEvent(eventUpdate);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(EVENT_1, result.getId());
    Assertions.assertEquals(TITLE_2, result.getTitle());
  }

  @Test
  public void updateEventShouldThrowNotFoundTest() {
    var event = EventTestUtils.buildEvent(EVENT_1, TITLE_1, DATE_1);
    when(eventRepository.getEventById(EVENT_1)).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> eventService.updateEvent(event));
  }

  @Test
  public void deleteEventTest() {
    var event = EventTestUtils.buildEvent(EVENT_1, TITLE_1, DATE_1);

    when(eventRepository.getEventById(EVENT_1)).thenReturn(Optional.of(event));
    when(eventRepository.delete(EVENT_1)).thenReturn(true);

    var result = eventService.deleteEvent(EVENT_1);
    Assertions.assertTrue(result);
  }

  @Test
  public void deleteEventShouldThrowNotFoundTest() {
    when(eventRepository.getEventById(EVENT_1)).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> eventService.deleteEvent(EVENT_1));
  }

}
