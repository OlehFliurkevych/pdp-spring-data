package com.fliurkevych.pdp.pdpspringcore.storage;

import com.fliurkevych.pdp.pdpspringcore.model.UserAccount;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Oleh Fliurkevych
 */
@Component
public interface UserAccountStorage {

  Optional<UserAccount> getUserAccountByUserId(Long userId);

  UserAccount update(UserAccount userAccount);

}
