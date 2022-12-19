package com.fliurkevych.pdp.pdpspringcore.controller;

import com.fliurkevych.pdp.pdpspringcore.dto.CreateUserAccountDto;
import com.fliurkevych.pdp.pdpspringcore.dto.UserAccountDto;
import com.fliurkevych.pdp.pdpspringcore.facade.BookingFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Oleh Fliurkevych
 */
@RestController
@RequestMapping("/user-accounts")
public class UserAccountController {

  private final BookingFacade bookingFacade;

  public UserAccountController(BookingFacade bookingFacade) {
    this.bookingFacade = bookingFacade;
  }

  @PostMapping(path = "/")
  public UserAccountDto create(@RequestBody CreateUserAccountDto createUserAccountDto) {
    return bookingFacade.createUserAccount(createUserAccountDto);
  }

  @PutMapping(path = "/")
  public UserAccountDto update(@RequestBody UserAccountDto userAccountDto) {
    return bookingFacade.updateUserAccount(userAccountDto);
  }

}
