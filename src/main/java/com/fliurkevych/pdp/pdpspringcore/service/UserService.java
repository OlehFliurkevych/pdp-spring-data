package com.fliurkevych.pdp.pdpspringcore.service;

import com.fliurkevych.pdp.pdpspringcore.exception.NotFoundException;
import com.fliurkevych.pdp.pdpspringcore.exception.ValidationException;
import com.fliurkevych.pdp.pdpspringcore.model.User;
import com.fliurkevych.pdp.pdpspringcore.storage.UserStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

  public User getUserById(Long userId) {
    log.info("Getting user by id: {}", userId);
    // TODO: consutructor of the NotFoundException can be customized, to omit unnecessary String.format each time
    return userStorage.getUserById(userId).orElseThrow(() -> new NotFoundException(
      String.format("Can not found element with key: [%s]", userId)));
  }

  public User getUserByEmail(String email) {
    log.info("Getting user by email: {}", email);
    return userStorage.getUserByEmail(email).orElseThrow(() -> new NotFoundException(
      String.format("Can not found user by email: [%s]", email)));
  }

  public List<User> getUsersByName(String name, Pageable pageable) {
    log.info("Getting users by name: {}", name);
    return userStorage.getUsersByName(name, pageable);
  }

  public User create(User user) {
    log.info("Creating new user with name [{}] and email [{}]", user.getName(), user.getEmail());

    // TODO: I'd suggest to add a sort of exists(user.getId()) method to the repository
    if (userStorage.getUserById(user.getId()).isEmpty()) {
      return userStorage.save(user);
    }
    throw new ValidationException(
      String.format("User with id [%s] have already created", user.getId()));
  }

  public User update(User user) {
    log.info("Updating user with id [{}]", user.getId());

    return userStorage.getUserById(user.getId())
      .map(userStorage::update)
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

  public List<User> getAllUsers() {
    log.info("Getting all users");

    return new ArrayList<>(userStorage.getAllUsers());
  }

}
