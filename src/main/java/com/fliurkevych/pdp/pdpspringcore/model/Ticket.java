package com.fliurkevych.pdp.pdpspringcore.model;

import com.fliurkevych.pdp.pdpspringcore.enums.TicketCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Table
@Entity(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "event_id", referencedColumnName = "id")
  private Event event;
  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
  @Column(columnDefinition = "BIGINT(20)")
  private Integer place;
  @Enumerated(value = EnumType.STRING)
  private TicketCategory category;

}
