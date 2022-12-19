package com.fliurkevych.pdp.pdpspringcore.mapper;

import com.fliurkevych.pdp.pdpspringcore.dto.TicketDto;
import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Oleh Fliurkevych
 */
@Mapper
  (uses = {EventMapper.class, UserMapper.class})
public interface TicketMapper {

  TicketMapper TICKET_MAPPER = Mappers.getMapper(TicketMapper.class);

  TicketDto entityToDto(Ticket ticket);

  Ticket dtoToEntity(TicketDto ticketDto);

}
