package com.fliurkevych.pdp.pdpspringcore.converter;

import com.fliurkevych.pdp.pdpspringcore.model.Ticket;
import com.fliurkevych.pdp.pdpspringcore.xml.TicketXml;
import com.fliurkevych.pdp.pdpspringcore.xml.TicketsXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oleh Fliurkevych
 */
@Component
public class TicketsConverter implements Converter<TicketsXml, List<Ticket>> {

  private final Converter<TicketXml, Ticket> ticketConverter;

  @Autowired
  public TicketsConverter(Converter<TicketXml, Ticket> ticketConverter) {
    this.ticketConverter = ticketConverter;
  }

  @Override
  public List<Ticket> convert(TicketsXml source) {
    return source.getTickets().stream()
      .map(ticketConverter::convert)
      .collect(Collectors.toList());
  }
}
