package com.fliurkevych.pdp.pdpspringcore.dto;

import com.fliurkevych.pdp.pdpspringcore.enums.TicketCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Oleh Fliurkevych
 */
@Data
@NoArgsConstructor
public class TicketDto {

  private Long id;
  private EventDto event;
  private UserDto user;
  private Integer place;
  private TicketCategory category;

}
