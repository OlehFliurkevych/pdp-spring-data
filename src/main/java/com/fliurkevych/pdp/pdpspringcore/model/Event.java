package com.fliurkevych.pdp.pdpspringcore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Data
@Table
@Entity(name = "event")
@NoArgsConstructor
@AllArgsConstructor
public class Event implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(columnDefinition = "VARCHAR(60)")
  private String title;
  @Temporal(TemporalType.DATE)
  private Date date;
  @Column(name = "ticket_price", columnDefinition = "DECIMAL(19,2)")
  private BigDecimal ticketPrice;

}
