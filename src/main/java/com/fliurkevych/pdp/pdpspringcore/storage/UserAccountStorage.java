package com.fliurkevych.pdp.pdpspringcore.storage;

import com.fliurkevych.pdp.pdpspringcore.model.UserAccount;
import org.springframework.stereotype.Component;

/**
 * @author Oleh Fliurkevych
 */
@Component
public interface UserAccountStorage {

  UserAccount save(UserAccount userAccount);

  UserAccount update(UserAccount userAccount);

}
