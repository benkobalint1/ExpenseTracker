package com.benkobalint1.expensetracker.repository;

import com.benkobalint1.expensetracker.domain.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author benkobalint1
 **/
@Repository
public interface HouseholdRepository extends JpaRepository<Household, Long> {

    List<Household> findByMembers_id(Long userId);
}
