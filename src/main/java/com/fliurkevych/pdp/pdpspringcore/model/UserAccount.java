package com.fliurkevych.pdp.pdpspringcore.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Oleh Fliurkevych
 */
@Data
@AllArgsConstructor
public class UserAccount {

  @Id
  private Long id;
  @Column(name = "user_id")
  @OneToOne(targetEntity = User.class, mappedBy = "id")
  private Long userId;
  private BigDecimal balance;

}
