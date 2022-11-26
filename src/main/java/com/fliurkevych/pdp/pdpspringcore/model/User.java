package com.fliurkevych.pdp.pdpspringcore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Table
@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(columnDefinition = "VARCHAR(60)")
  private String name;
  @Column(columnDefinition = "VARCHAR(60)")
  private String email;
  @OneToOne
  @JoinColumn(name = "user_account_id", referencedColumnName = "id")
  private UserAccount userAccount;

}
