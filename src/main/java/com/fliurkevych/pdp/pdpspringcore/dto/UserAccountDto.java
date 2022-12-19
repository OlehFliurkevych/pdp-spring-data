package com.fliurkevych.pdp.pdpspringcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Oleh Fliurkevych
 */
@Data
@AllArgsConstructor
public class UserAccountDto {

  private Long id;
  private UserDto userDto;
  private BigDecimal balance;

}
