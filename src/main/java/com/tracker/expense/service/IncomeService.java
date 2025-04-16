package com.tracker.expense.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.expense.entity.Income;
import com.tracker.expense.repository.IncomeRepository;

@Service
public class IncomeService {
	
	@Autowired
	private IncomeRepository incomeRepository;

	public Income addIncome(Income income) {
		try {
			Income save = incomeRepository.save(income);
			return save;
		} catch (Exception e) {
			return null;
		}
		
	}
}
