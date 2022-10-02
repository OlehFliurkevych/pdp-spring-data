package com.fliurkevych.pdp.pdpspringcore.util;

import com.fliurkevych.pdp.pdpspringcore.enums.TicketCategory;
import com.fliurkevych.pdp.pdpspringcore.xml.TicketXml;
import com.fliurkevych.pdp.pdpspringcore.xml.TicketsXml;

import java.util.ArrayList;

/**
 * @author Oleh Fliurkevych
 */
public final class TicketTestUtils {

  public static TicketsXml buildTicketsXml(int countTickets) {
    var ticketsXml = new TicketsXml();

    var tickets = new ArrayList();
    for (int i = 0; i < countTickets; i++) {
      var ticket = new TicketXml();
      ticket.setCategory(i % 2 == 0 ? TicketCategory.STANDARD : TicketCategory.BAR);
      ticket.setEventId((long) i * 12);
      ticket.setUserId((long) i * 15);
      ticket.setPlace(i);
      tickets.add(ticket);
    }

    ticketsXml.setTickets(tickets);
    return ticketsXml;
  }

}
