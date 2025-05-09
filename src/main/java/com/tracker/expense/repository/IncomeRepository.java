package com.tracker.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tracker.expense.entity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {

}
