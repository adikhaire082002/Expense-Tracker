package com.tracker.expense.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.expense.entity.Expense;
import com.tracker.expense.repository.ExpenseRepository;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseRepository repository;

	public Expense addExpense(Expense expense) {
		try {
			Expense save = repository.save(expense);
			return save;
		} catch (Exception e) {
			
		}
		return null;
	}

}
