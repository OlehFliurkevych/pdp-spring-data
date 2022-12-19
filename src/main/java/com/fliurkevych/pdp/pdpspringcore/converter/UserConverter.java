package com.fliurkevych.pdp.pdpspringcore.converter;

import static com.fliurkevych.pdp.pdpspringcore.mapper.UserMapper.USER_MAPPER;

import com.fliurkevych.pdp.pdpspringcore.dto.UserDto;
import com.fliurkevych.pdp.pdpspringcore.model.User;

/**
 * @author Oleh Fliurkevych
 */
public final class UserConverter {

  public static User dtoToEntity(UserDto userDto) {
    return USER_MAPPER.dtoToEntity(userDto);
  }

  public static UserDto entityToDto(User user) {
    return USER_MAPPER.entityToDto(user);
  }

}
