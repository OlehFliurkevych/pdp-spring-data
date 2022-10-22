package com.fliurkevych.pdp.pdpspringcore.storage.db.impl;

import com.fliurkevych.pdp.pdpspringcore.model.User;
import com.fliurkevych.pdp.pdpspringcore.storage.UserStorage;
import com.fliurkevych.pdp.pdpspringcore.storage.db.DbUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Oleh Fliurkevych
 */
@Slf4j
@Service
public class DbUserStorage implements UserStorage {

  private final DbUserRepository dbUserRepository;

  public DbUserStorage(DbUserRepository dbUserRepository) {
    this.dbUserRepository = dbUserRepository;
  }

  @Override
  public Optional<User> getUserById(Long userId) {
    return dbUserRepository.findById(userId);
  }

  @Override
  public Optional<User> getUserByEmail(String email) {
    return dbUserRepository.findByEmail(email);
  }

  @Override
  public List<User> getUsersByName(String name, Pageable pageable) {
    return dbUserRepository.findAllByNameLike(name, pageable);
  }

  @Override
  public User save(User user) {
    return dbUserRepository.save(user);
  }

  @Override
  public User update(User user) {
    return dbUserRepository.save(user);
  }

  @Override
  public boolean delete(Long userId) {
    dbUserRepository.deleteById(userId);
    return !dbUserRepository.existsById(userId);
  }

  @Override
  public Collection<User> getAllUsers() {
    return dbUserRepository.findAll(Pageable.ofSize(Integer.MAX_VALUE)).getContent();
  }
}
