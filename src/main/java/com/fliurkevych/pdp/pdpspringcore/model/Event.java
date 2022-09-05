package com.fliurkevych.pdp.pdpspringcore.model;

import lombok.Data;

import java.util.Date;

@Data
public class Event {
    
  private Long id;
  private String title;
  private Date date;

}
