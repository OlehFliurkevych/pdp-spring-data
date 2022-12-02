package com.fliurkevych.pdp.pdpspringcore.storage.db;

import com.fliurkevych.pdp.pdpspringcore.model.UserAccount;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Oleh Fliurkevych
 */
@Repository
public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {

  Optional<UserAccount> findByUserId(Long userId);

}
