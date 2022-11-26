package com.fliurkevych.pdp.pdpspringcore.converter;

import com.fliurkevych.pdp.pdpspringcore.model.Event;
import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import com.fliurkevych.pdp.pdpspringcore.xml.TicketXml;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Oleh Fliurkevych
 */
@Component
public final class TicketConverter implements Converter<TicketXml, Ticket> {

  @Override
  public Ticket convert(TicketXml ticketXml) {
    var ticket = new Ticket();
//    ticket.setEvent(ticketXml.getEventId());
//    ticket.setUserId(ticketXml.getUserId());
    ticket.setPlace(ticketXml.getPlace());
    ticket.setCategory(ticketXml.getCategory());
    return ticket;
  }
}
