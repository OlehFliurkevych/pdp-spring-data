package com.fliurkevych.pdp.pdpspringcore.util;

import com.fliurkevych.pdp.pdpspringcore.dto.UserDto;
import com.fliurkevych.pdp.pdpspringcore.model.User;

/**
 * @author Oleh Fliurkevych
 */
public final class UserTestUtils {

  public static final Long USER_ID_1 = 111L;
  public static final Long USER_ID_2 = 222L;
  public static final String NAME_1 = "name 111";
  public static final String NAME_2 = "name 112";
  public static final String EMAIL_1 = "name1@gmail.com";
  public static final String EMAIL_2 = "name2@gmail.com";

  public static User buildUser(Long id, String name, String email) {
    var user = new User();
    user.setId(id);
    user.setName(name);
    user.setEmail(email);
    return user;
  }

  public static UserDto buildUserDto(Long id, String name, String email) {
    var user = new UserDto();
    user.setId(id);
    user.setName(name);
    user.setEmail(email);
    return user;
  }

}
