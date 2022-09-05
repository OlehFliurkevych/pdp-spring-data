package com.fliurkevych.pdp.pdpspringcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PdpSpringCoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(PdpSpringCoreApplication.class, args);
  }

}
