package com.fliurkevych.pdp.pdpspringcore.xml;

import com.fliurkevych.pdp.pdpspringcore.dto.BookTicketDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Oleh Fliurkevych
 */
@Data
@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
@EqualsAndHashCode(callSuper = true)
public class TicketXml extends BookTicketDto {

}
