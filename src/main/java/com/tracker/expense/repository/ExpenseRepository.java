package com.tracker.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tracker.expense.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer>{

	

}
