package com.fliurkevych.pdp.pdpspringcore.util;

import com.fliurkevych.pdp.pdpspringcore.dto.EventDto;
import com.fliurkevych.pdp.pdpspringcore.dto.UserAccountDto;
import com.fliurkevych.pdp.pdpspringcore.exception.ValidationException;

import java.math.BigDecimal;

/**
 * @author Oleh Fliurkevych
 */
public final class ValidationUtils {

  public static void validatePlaceNumber(int place) {
    if (place > 100 || place <= 0) {
      throw new ValidationException("Entered incorrect place number");
    }
  }

  public static void validateUserBalance(UserAccountDto userAccount, EventDto event) {
    var balance = userAccount.getBalance();
    if (balance.equals(BigDecimal.ZERO)) {
      throw new ValidationException("Balance of user account [%s] is empty", userAccount.getId());
    }
    if (balance.compareTo(event.getTicketPrice()) < 0) {
      throw new ValidationException("User with account [%s] don't have enough money",
        userAccount.getId());
    }
  }

}
