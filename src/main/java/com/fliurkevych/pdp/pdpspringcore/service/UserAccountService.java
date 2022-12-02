package com.fliurkevych.pdp.pdpspringcore.service;

import static com.fliurkevych.pdp.pdpspringcore.util.ValidationUtils.validateUserBalance;

import com.fliurkevych.pdp.pdpspringcore.exception.NotFoundException;
import com.fliurkevych.pdp.pdpspringcore.model.Event;
import com.fliurkevych.pdp.pdpspringcore.model.UserAccount;
import com.fliurkevych.pdp.pdpspringcore.storage.UserAccountStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Service
public class UserAccountService {

  private final UserAccountStorage userAccountStorage;

  public UserAccountService(UserAccountStorage userAccountStorage) {
    this.userAccountStorage = userAccountStorage;
  }

  public UserAccount getUserAccountByUserId(Long userId) {
    log.info("Getting user account by user id: {}", userId);
    return userAccountStorage.getUserAccountByUserId(userId)
      .orElseThrow(
        () -> new NotFoundException(
          String.format("Can not found user account by user id: [%s]", userId)));
  }

  public UserAccount update(UserAccount userAccount) {
    log.info("Updating user account with id [{}] for user with id [{}]", userAccount.getId(),
      userAccount.getUser().getId());
    return userAccountStorage.update(userAccount);
  }

  public UserAccount reduceUserAccountBalance(UserAccount userAccount,
    Event event) {
    log.info("Try to reduce user account with id [{}] for event with id [{}]",
      userAccount.getId(), event.getId());
    validateUserBalance(userAccount, event);
    userAccount.setBalance(userAccount.getBalance().subtract(event.getTicketPrice()));
    return this.update(userAccount);
  }

}
