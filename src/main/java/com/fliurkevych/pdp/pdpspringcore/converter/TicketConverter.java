package com.fliurkevych.pdp.pdpspringcore.converter;

import static com.fliurkevych.pdp.pdpspringcore.mapper.TicketMapper.TICKET_MAPPER;

import com.fliurkevych.pdp.pdpspringcore.dto.BookTicketDto;
import com.fliurkevych.pdp.pdpspringcore.dto.TicketDto;
import com.fliurkevych.pdp.pdpspringcore.model.Ticket;

/**
 * @author Oleh Fliurkevych
 */
public class TicketConverter {

  public static TicketDto entityToDto(Ticket ticket) {
    return TICKET_MAPPER.entityToDto(ticket);
  }

  public static Ticket dtoToEntity(TicketDto ticketDto) {
    return TICKET_MAPPER.dtoToEntity(ticketDto);
  }
  
}
