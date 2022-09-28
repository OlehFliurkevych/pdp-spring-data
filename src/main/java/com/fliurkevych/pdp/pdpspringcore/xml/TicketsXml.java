package com.fliurkevych.pdp.pdpspringcore.xml;

import lombok.Data;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Oleh Fliurkevych
 */
@Data
@XmlRootElement(name = "tickets")
public class TicketsXml {

  @XmlElement(name = "ticket")
  private List<TicketXml> tickets;

}
