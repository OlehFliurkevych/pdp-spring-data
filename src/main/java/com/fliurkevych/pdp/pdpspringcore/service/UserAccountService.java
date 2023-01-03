package com.fliurkevych.pdp.pdpspringcore.service;

import static com.fliurkevych.pdp.pdpspringcore.converter.UserAccountConverter.createDtoToEntity;
import static com.fliurkevych.pdp.pdpspringcore.converter.UserAccountConverter.dtoToEntity;
import static com.fliurkevych.pdp.pdpspringcore.converter.UserAccountConverter.entityToDto;
import static com.fliurkevych.pdp.pdpspringcore.util.ValidationUtils.validateUserBalance;

import com.fliurkevych.pdp.pdpspringcore.dto.CreateUserAccountDto;
import com.fliurkevych.pdp.pdpspringcore.dto.EventDto;
import com.fliurkevych.pdp.pdpspringcore.dto.UserAccountDto;
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
  private final UserService userService;

  public UserAccountService(UserAccountStorage userAccountStorage, UserService userService) {
    this.userAccountStorage = userAccountStorage;
    this.userService = userService;
  }

  public UserAccountDto create(CreateUserAccountDto createUserAccountDto) {
    log.info("Creating new user account for user with id [{}]", createUserAccountDto.getUserId());
    var userDto = userService.getUserById(createUserAccountDto.getUserId());

    var created = userAccountStorage.save(createDtoToEntity(createUserAccountDto, userDto));
    log.info("Successfully created user account for user with id [{}]",
      createUserAccountDto.getUserId());
    return entityToDto(created);
  }

  public UserAccountDto update(UserAccountDto userAccount) {
    log.info("Updating user account with id [{}]", userAccount.getId());
    var updated = userAccountStorage.update(dtoToEntity(userAccount));
    log.info("Successfully update user account with id [{}]", updated.getId());
    return entityToDto(updated);
  }

  public UserAccountDto reduceUserAccountBalance(UserAccountDto userAccount,
    EventDto event) {
    log.info("Try to reduce user account with id [{}] for event with id [{}]",
      userAccount.getId(), event.getId());
    validateUserBalance(userAccount, event);
    userAccount.setBalance(userAccount.getBalance().subtract(event.getTicketPrice()));
    return update(userAccount);
  }

}
