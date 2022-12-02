package com.fliurkevych.pdp.pdpspringcore.storage.db.impl;

import com.fliurkevych.pdp.pdpspringcore.model.UserAccount;
import com.fliurkevych.pdp.pdpspringcore.storage.UserAccountStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.UserAccountRepository;

import java.util.Optional;

/**
 * @author Oleh Fliurkevych
 */
public class DbUserAccountStorage implements UserAccountStorage {

  private final UserAccountRepository userAccountRepository;

  public DbUserAccountStorage(UserAccountRepository userAccountRepository) {
    this.userAccountRepository = userAccountRepository;
  }

  @Override
  public Optional<UserAccount> getUserAccountByUserId(Long userId) {
    return userAccountRepository.findByUserId(userId);
  }

  @Override
  public UserAccount update(UserAccount userAccount) {
    return userAccountRepository.save(userAccount);
  }

}
