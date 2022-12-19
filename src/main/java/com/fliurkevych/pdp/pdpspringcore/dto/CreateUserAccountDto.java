package com.fliurkevych.pdp.pdpspringcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Oleh Fliurkevych
 */
@Data
@AllArgsConstructor
public class CreateUserAccountDto {

  private Long userId;
  private BigDecimal balance;

}
