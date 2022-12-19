package com.fliurkevych.pdp.pdpspringcore.mapper;

import com.fliurkevych.pdp.pdpspringcore.dto.CreateUserAccountDto;
import com.fliurkevych.pdp.pdpspringcore.dto.UserAccountDto;
import com.fliurkevych.pdp.pdpspringcore.model.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Oleh Fliurkevych
 */
@Mapper(uses = {UserMapper.class})
public interface UserAccountMapper {

  UserAccountMapper USER_ACCOUNT_MAPPER = Mappers.getMapper(UserAccountMapper.class);

  UserAccount createDtoToEntity(CreateUserAccountDto createUserAccountDto);

  UserAccountDto entityToDto(UserAccount userAccount);

  UserAccount dtoToEntity(UserAccountDto userAccountDto);

}
