package com.fliurkevych.pdp.pdpspringcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PdpSpringDataApplication {

  public static void main(String[] args) {
    SpringApplication.run(PdpSpringDataApplication.class, args);
  }

}
