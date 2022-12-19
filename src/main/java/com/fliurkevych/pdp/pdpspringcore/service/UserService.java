package com.fliurkevych.pdp.pdpspringcore.service;

import static com.fliurkevych.pdp.pdpspringcore.converter.UserConverter.dtoToEntity;
import static com.fliurkevych.pdp.pdpspringcore.converter.UserConverter.entityToDto;

import com.fliurkevych.pdp.pdpspringcore.converter.UserConverter;
import com.fliurkevych.pdp.pdpspringcore.dto.UserDto;
import com.fliurkevych.pdp.pdpspringcore.exception.NotFoundException;
import com.fliurkevych.pdp.pdpspringcore.exception.ValidationException;
import com.fliurkevych.pdp.pdpspringcore.storage.UserStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Service
public class UserService {

  private final UserStorage userStorage;

  @Autowired
  public UserService(UserStorage userStorage) {
    this.userStorage = userStorage;
  }

  public UserDto getUserById(Long userId) {
    log.info("Getting user by id: {}", userId);
    return userStorage.getUserById(userId)
      .map(UserConverter::entityToDto)
      .orElseThrow(() -> new NotFoundException(
        String.format("Can not found element with key: [%s]", userId)));
  }

  public UserDto getUserByEmail(String email) {
    log.info("Getting user by email: {}", email);
    return userStorage.getUserByEmail(email)
      .map(UserConverter::entityToDto)
      .orElseThrow(() -> new NotFoundException(
        String.format("Can not found user by email: [%s]", email)));
  }

  public List<UserDto> getUsersByName(String name, Pageable pageable) {
    log.info("Getting users by name: {}", name);
    return userStorage.getUsersByName(name, pageable).stream()
      .map(UserConverter::entityToDto)
      .collect(Collectors.toList());
  }

  public UserDto create(UserDto userDto) {
    log.info("Creating new user with name [{}] and email [{}]", userDto.getName(),
      userDto.getEmail());

    if (!userStorage.exists(userDto.getId())) {
      return entityToDto(userStorage.save(dtoToEntity(userDto)));
    }
    throw new ValidationException("User with id [%s] have already created", userDto.getId());
  }

  public UserDto update(UserDto user) {
    log.info("Updating user with id [{}]", user.getId());

    return userStorage.getUserById(user.getId())
      .map(userStorage::update)
      .map(UserConverter::entityToDto)
      .orElseThrow(() -> new NotFoundException(
        String.format("Can not found element with key: [%s]", user.getId())));
  }

  public boolean delete(Long userId) {
    log.info("Deleting user with id [{}]", userId);
    return userStorage.getUserById(userId)
      .map(user -> userStorage.delete(user.getId()))
      .orElseThrow(() -> new NotFoundException(
        String.format("Can not found element with key: [%s]", userId)));
  }

  public List<UserDto> getAllUsers() {
    log.info("Getting all users");

    return userStorage.getAllUsers().stream()
      .map(UserConverter::entityToDto)
      .collect(Collectors.toList());
  }

}
