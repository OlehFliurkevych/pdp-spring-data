package com.fliurkevych.pdp.pdpspringcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Oleh Fliurkevych
 */
@Data
@AllArgsConstructor
public class EventDto {

  private Long id;
  private String title;
  private Date date;
  private BigDecimal ticketPrice;

}
