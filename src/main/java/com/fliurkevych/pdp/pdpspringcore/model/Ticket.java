package com.fliurkevych.pdp.pdpspringcore.model;

import com.fliurkevych.pdp.pdpspringcore.enums.TicketCategory;
import lombok.Data;

@Data
public class Ticket {

  private Long id;
  private Long eventId;
  private Long userId;
  private Integer place;
  private TicketCategory category;
  
}
