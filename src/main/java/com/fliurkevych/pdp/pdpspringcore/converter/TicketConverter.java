package com.fliurkevych.pdp.pdpspringcore.converter;

import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import com.fliurkevych.pdp.pdpspringcore.xml.TicketXml;
import com.fliurkevych.pdp.pdpspringcore.xml.TicketsXml;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oleh Fliurkevych
 */
public final class TicketConverter {

  public static Ticket toTicket(TicketXml ticketXml) {
    var ticket = new Ticket();
    ticket.setEventId(ticketXml.getEventId());
    ticket.setUserId(ticketXml.getUserId());
    ticket.setPlace(ticketXml.getPlace());
    ticket.setCategory(ticketXml.getCategory());
    return ticket;
  }

  public static List<Ticket> toTickets(TicketsXml ticketsXml) {
    return ticketsXml.getTickets().stream().map(TicketConverter::toTicket)
      .collect(Collectors.toList());
  }

}
