package com.fliurkevych.pdp.pdpspringcore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToOne(mappedBy = "userAccount")
  private User user;
  @Column(columnDefinition = "DECIMAL(19,2)")
  private BigDecimal balance;

}
