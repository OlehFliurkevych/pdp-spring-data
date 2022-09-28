package com.fliurkevych.pdp.pdpspringcore.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Oleh Fliurkevych
 */
@Data
@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketXml {

  private Long userId;
  private Long eventId;
  private Long place;

}
