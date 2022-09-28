package com.fliurkevych.pdp.pdpspringcore.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Oleh Fliurkevych
 */
@Data
@XmlRootElement(name = "ticket")
public class TicketXml {

  @XmlAttribute(name = "user_id")
  private Long userId;
  @XmlAttribute(name = "event_id")
  private Long eventId;
  @XmlAttribute(name = "place")
  private Long place;

}
