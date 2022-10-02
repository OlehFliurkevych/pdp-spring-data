package com.fliurkevych.pdp.pdpspringcore.model;

import com.fliurkevych.pdp.pdpspringcore.enums.TicketCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

  private Long id;
  private Long eventId;
  private Long userId;
  private Integer place;
  private TicketCategory category;
  
}
