package com.fliurkevych.pdp.pdpspringcore.converter;

import static com.fliurkevych.pdp.pdpspringcore.mapper.EventMapper.EVENT_MAPPER;

import com.fliurkevych.pdp.pdpspringcore.dto.EventDto;
import com.fliurkevych.pdp.pdpspringcore.model.Event;

/**
 * @author Oleh Fliurkevych
 */
public final class EventConverter {

  public static EventDto entityToDto(Event event) {
    return EVENT_MAPPER.entityToDto(event);
  }

  public static Event dtoToEntity(EventDto eventDto) {
    return EVENT_MAPPER.dtoToEntity(eventDto);
  }

}
