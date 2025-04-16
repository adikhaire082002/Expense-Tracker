package com.tracker.expense.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.expense.entity.Income;
import com.tracker.expense.entity.User;
import com.tracker.expense.service.IncomeService;
import com.tracker.expense.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class IncomeController {
	
	@Autowired
	private IncomeService service;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/income")
	public ResponseEntity<Object> addIcome(@RequestBody Income income,HttpSession httpSession) {
		User user = (User)httpSession.getAttribute("user");
		if(user != null) {
			income.setUser(user);
			Income user2 = service.addIncome(income);
			if(user2 != null) {
				user.setAmount(user.getAmount()+ income.getAmount());
				userService.addUser(user);
				return new ResponseEntity<Object>("Income added " +income.toString() + user.toString(),HttpStatus.ACCEPTED);
			}
			else {
				return new ResponseEntity<Object>("Something went wrong ",HttpStatus.BAD_REQUEST);
			}
		}
		else {
			return new ResponseEntity<Object>("Login first",HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
}
