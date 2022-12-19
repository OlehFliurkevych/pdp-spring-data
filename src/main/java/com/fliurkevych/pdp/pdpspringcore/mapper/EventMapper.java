package com.fliurkevych.pdp.pdpspringcore.mapper;

import com.fliurkevych.pdp.pdpspringcore.dto.EventDto;
import com.fliurkevych.pdp.pdpspringcore.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Oleh Fliurkevych
 */
@Mapper
public interface EventMapper {

  EventMapper EVENT_MAPPER = Mappers.getMapper(EventMapper.class);

  EventDto entityToDto(Event event);

  Event dtoToEntity(EventDto eventDto);

}
