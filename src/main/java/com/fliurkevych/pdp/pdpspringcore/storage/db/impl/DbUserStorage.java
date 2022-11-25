package com.fliurkevych.pdp.pdpspringcore.storage.db.impl;

import com.fliurkevych.pdp.pdpspringcore.model.User;
import com.fliurkevych.pdp.pdpspringcore.storage.UserStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
public class DbUserStorage implements UserStorage {

  private final UserRepository userRepository;
  
  public DbUserStorage(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Optional<User> getUserById(Long userId) {
    return userRepository.findById(userId);
  }

  @Override
  public Optional<User> getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public List<User> getUsersByName(String name, Pageable pageable) {
    return userRepository.findAllByNameLike(name, pageable);
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public User update(User user) {
    return userRepository.save(user);
  }

  @Override
  public boolean delete(Long userId) {
    userRepository.deleteById(userId);
    return !userRepository.existsById(userId);
  }

  @Override
  public Collection<User> getAllUsers() {
    return null;
  }
}
