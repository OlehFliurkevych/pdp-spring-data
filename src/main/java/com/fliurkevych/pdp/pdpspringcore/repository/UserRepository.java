package com.fliurkevych.pdp.pdpspringcore.repository;

import com.fliurkevych.pdp.pdpspringcore.model.User;
import com.fliurkevych.pdp.pdpspringcore.storage.UserStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Component
public class UserRepository {

  private final UserStorage userStorage;

  @Autowired
  public UserRepository(UserStorage userStorage) {
    this.userStorage = userStorage;
  }

  public Optional<User> getUserById(Long userId) {
    return userStorage.getUserById(userId);
  }

  public Optional<User> getUserByEmail(String email) {
    return userStorage.getUserByEmail(email);
  }

  public List<User> getUsersByName(String name, Pageable pageable) {
    return userStorage.getUsersByName(name, pageable);
  }

  public User save(User user) {
    return userStorage.save(user);
  }

  public User update(User user) {
    return userStorage.update(user);
  }

  public boolean delete(Long userId) {
    return userStorage.delete(userId);
  }

  public Collection<User> getAllUsers() {
    return userStorage.getAllUsers();
  }

}
