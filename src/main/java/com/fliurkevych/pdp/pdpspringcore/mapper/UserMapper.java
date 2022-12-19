package com.fliurkevych.pdp.pdpspringcore.mapper;

import com.fliurkevych.pdp.pdpspringcore.dto.UserDto;
import com.fliurkevych.pdp.pdpspringcore.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Oleh Fliurkevych
 */
@Mapper(uses = {UserAccountMapper.class})
public interface UserMapper {

  UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

  User dtoToEntity(UserDto userDto);

  UserDto entityToDto(User user);

}
