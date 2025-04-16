package com.tracker.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.expense.entity.Expense;
import com.tracker.expense.entity.Income;
import com.tracker.expense.entity.User;
import com.tracker.expense.service.ExpenseService;
import com.tracker.expense.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class ExpenseController {
	
	@Autowired
	private ExpenseService service;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/expense")
	public ResponseEntity<Object> addIcome(@RequestBody Expense expense,HttpSession httpSession) {
		User user = (User)httpSession.getAttribute("user");
		if(user != null) {
			expense.setUser(user);
			Expense user2 = service.addExpense(expense);
			if(user2 != null) {
				user.setAmount(user.getAmount()-expense.getAmount());
				userService.addUser(user);
				return new ResponseEntity<Object>("Expense added " +expense.toString() + user.toString(),HttpStatus.ACCEPTED);
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
