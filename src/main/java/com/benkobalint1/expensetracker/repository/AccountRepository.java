package com.benkobalint1.expensetracker.repository;

import com.benkobalint1.expensetracker.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author benkobalint
 **/
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
