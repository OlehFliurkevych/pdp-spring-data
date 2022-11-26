package com.fliurkevych.pdp.pdpspringcore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Oleh Fliurkevych
 */
@Data
@Table
@Entity(name = "user_account")
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount implements Serializable {

  @Id
  private Long id;
  @OneToOne(mappedBy = "userAccount")
  private User user;
  private BigDecimal balance;

}
