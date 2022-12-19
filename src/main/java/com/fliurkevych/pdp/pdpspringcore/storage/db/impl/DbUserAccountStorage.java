package com.fliurkevych.pdp.pdpspringcore.storage.db.impl;

import com.fliurkevych.pdp.pdpspringcore.model.UserAccount;
import com.fliurkevych.pdp.pdpspringcore.storage.UserAccountStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.UserAccountRepository;

/**
 * @author Oleh Fliurkevych
 */
public class DbUserAccountStorage implements UserAccountStorage {

  private final UserAccountRepository userAccountRepository;

  public DbUserAccountStorage(UserAccountRepository userAccountRepository) {
    this.userAccountRepository = userAccountRepository;
  }

  @Override
  public UserAccount update(UserAccount userAccount) {
    return userAccountRepository.save(userAccount);
  }

  @Override
  public UserAccount save(UserAccount userAccount) {
    return userAccountRepository.save(userAccount);
  }
}
