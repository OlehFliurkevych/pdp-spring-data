package com.fliurkevych.pdp.pdpspringcore.service;

import com.fliurkevych.pdp.pdpspringcore.exception.NotFoundException;
import com.fliurkevych.pdp.pdpspringcore.exception.ValidationException;
import com.fliurkevych.pdp.pdpspringcore.model.User;
import com.fliurkevych.pdp.pdpspringcore.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User getUserById(Long userId) {
    log.info("Getting user by id: {}", userId);
    return userRepository.getUserById(userId).orElseThrow(() -> new NotFoundException(
      String.format("Can not found element with key: [%s]", userId)));
  }

  public User getUserByEmail(String email) {
    log.info("Getting user by email: {}", email);
    return userRepository.getUserByEmail(email).orElseThrow(() -> new NotFoundException(
      String.format("Can not found user by email: [%s]", email)));
  }

  public List<User> getUsersByName(String name, int pageSize, int pageNum) {
    log.info("Getting users by name: {}", name);
    return userRepository.getUsersByName(name, pageSize, pageNum);
  }

  public User createUser(User user) {
    log.info("Creating new user with name [{}] and email [{}]",
      user.getName(), user.getEmail());
    var optional = userRepository.getUserById(user.getId());
    if (optional.isEmpty()) {
      return userRepository.save(user);
    } else {
      throw new ValidationException(
        String.format("User with id [%s] have already created", user.getId()));
    }
  }

  public User updateUser(User user) {
    log.info("Updating user with id [{}]", user.getId());
    var optional = userRepository.getUserById(user.getId());
    if (optional.isPresent()) {
      return userRepository.update(user);
    } else {
      throw new ValidationException(
        String.format("Can not found element with key: [%s]", user.getId()));
    }
  }

  public boolean deleteUser(Long userId) {
    log.info("Deleting user with id [{}]", userId);
    var optional = userRepository.getUserById(userId);
    if (optional.isPresent()) {
      return userRepository.delete(userId);
    } else {
      throw new ValidationException(
        String.format("Can not found element with key: [%s]", userId));
    }
  }

}
