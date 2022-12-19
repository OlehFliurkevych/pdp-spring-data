package com.fliurkevych.pdp.pdpspringcore.converter;

import static com.fliurkevych.pdp.pdpspringcore.mapper.UserAccountMapper.USER_ACCOUNT_MAPPER;

import com.fliurkevych.pdp.pdpspringcore.dto.CreateUserAccountDto;
import com.fliurkevych.pdp.pdpspringcore.dto.UserAccountDto;
import com.fliurkevych.pdp.pdpspringcore.dto.UserDto;
import com.fliurkevych.pdp.pdpspringcore.model.UserAccount;

/**
 * @author Oleh Fliurkevych
 */
public final class UserAccountConverter {

  public static UserAccount createDtoToEntity(CreateUserAccountDto createUserAccountDto,
    UserDto userDto) {
    return USER_ACCOUNT_MAPPER.createDtoToEntity(createUserAccountDto);
  }

  public static UserAccount dtoToEntity(UserAccountDto userAccountDto) {
    return USER_ACCOUNT_MAPPER.dtoToEntity(userAccountDto);
  }

  public static UserAccountDto entityToDto(UserAccount userAccount) {
    return USER_ACCOUNT_MAPPER.entityToDto(userAccount);
  }
}
