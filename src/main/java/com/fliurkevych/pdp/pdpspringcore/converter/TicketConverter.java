package com.fliurkevych.pdp.pdpspringcore.converter;

import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import com.fliurkevych.pdp.pdpspringcore.storage.EventStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.UserStorage;
import com.fliurkevych.pdp.pdpspringcore.xml.TicketXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Oleh Fliurkevych
 */
@Component
public class TicketConverter implements Converter<TicketXml, Ticket> {

  private final EventStorage eventStorage;
  private final UserStorage userStorage;

  @Autowired
  public TicketConverter(EventStorage eventStorage, UserStorage userStorage) {
    this.eventStorage = eventStorage;
    this.userStorage = userStorage;
  }

  @Override
  public Ticket convert(TicketXml ticketXml) {
    var ticket = new Ticket();
    userStorage.getUserById(ticketXml.getUserId())
      .ifPresent(ticket::setUser);
    eventStorage.getEventById(ticketXml.getEventId())
      .ifPresent(ticket::setEvent);
    ticket.setPlace(ticketXml.getPlace());
    ticket.setCategory(ticketXml.getCategory());
    return ticket;
  }
}
