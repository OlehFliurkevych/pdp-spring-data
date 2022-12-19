package com.fliurkevych.pdp.pdpspringcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Oleh Fliurkevych
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private Long id;
  private String name;
  private String email;
  private UserAccountDto userAccount;

}
