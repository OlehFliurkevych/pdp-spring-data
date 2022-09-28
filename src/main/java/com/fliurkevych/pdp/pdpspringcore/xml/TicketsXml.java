package com.fliurkevych.pdp.pdpspringcore.xml;

import lombok.Data;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Oleh Fliurkevych
 */
@Data
@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketsXml {

  @XmlElement(name = "ticket")
  private List<TicketXml> tickets;

}
