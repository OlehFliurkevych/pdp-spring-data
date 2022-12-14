package com.fliurkevych.pdp.pdpspringcore.dto;

import com.fliurkevych.pdp.pdpspringcore.enums.TicketCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Oleh Fliurkevych
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookTicketDto {

  private Long userId;
  private Long eventId;
  private Integer place;
  private TicketCategory category;

}
