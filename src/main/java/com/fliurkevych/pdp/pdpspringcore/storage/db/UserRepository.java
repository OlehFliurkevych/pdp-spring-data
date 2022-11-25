package com.fliurkevych.pdp.pdpspringcore.storage.db;

import com.fliurkevych.pdp.pdpspringcore.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Oleh Fliurkevych
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

  Optional<User> findByEmail(String email);

  List<User> findAllByNameLike(String name, Pageable pageable);

}
