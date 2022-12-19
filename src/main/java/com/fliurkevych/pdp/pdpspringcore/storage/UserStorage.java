package com.fliurkevych.pdp.pdpspringcore.storage;

import com.fliurkevych.pdp.pdpspringcore.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Oleh Fliurkevych
 */
@Component
public interface UserStorage {

  Optional<User> getUserById(Long userId);

  Optional<User> getUserByEmail(String email);

  List<User> getUsersByName(String name, Pageable pageable);

  User save(User user);

  User update(User user);

  boolean delete(Long userId);

  Collection<User> getAllUsers();

  boolean exists(Long id);

}
