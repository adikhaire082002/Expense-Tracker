package com.tracker.expense.controller;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tracker.expense.entity.Expense;
import com.tracker.expense.entity.Income;
import com.tracker.expense.entity.User;
import com.tracker.expense.service.UserService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping(path = "/signup")
	private ResponseEntity<Object> addUser(@RequestBody User user ){
		
		
		User user2 = service.addUser(user);
		if(user2 != null) {
			return new ResponseEntity<Object>("User add Succesfully \n" +user2 , HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<Object>("Username or mobile is already exited" ,HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PutMapping("/updateuser")
	public ResponseEntity<Object> updateUser(@RequestBody User user, HttpSession httpSession) {
		User user1 = (User)httpSession.getAttribute("user");
		if(user1 != null) {
			User byUser = service.findByUser(user.getUsername());
			if(byUser != null) {
				service.addUser(byUser);
				return new ResponseEntity<Object>(user.toString() , HttpStatus.ACCEPTED);
			}
			else {
				return new ResponseEntity<Object>("user not found",HttpStatus.UNAUTHORIZED);
			}
		}
		else {
			return new ResponseEntity<Object>("login first" , HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deleteuser")
	private ResponseEntity<Object> deleteUser( HttpSession httpSession) {
		User user1= (User) httpSession.getAttribute("user");
		
		if(user1 != null) {
			User byUser = service.findByUser(user1.getUsername());
			if(byUser!=null) {
				service.deleteUser(user1.getUsername());
				return new ResponseEntity<Object>("User Deleted" , HttpStatus.ACCEPTED);
			}
			else {
				return new ResponseEntity<Object>("Something went wrong" , HttpStatus.BAD_REQUEST);
			}
		}
		else {
			return new ResponseEntity<Object>("login first" , HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "/transactions")
	protected ResponseEntity<Object> getAllTransaction(HttpSession httpSession){
		User user = (User)httpSession.getAttribute("user");
		if(user != null) {
			List transactions = new ArrayList<>();
			List <Income> incomes = user.getIncomes();
			List <Expense> expenses = user.getExpenses(); 
			for (Expense expense : expenses) {
				transactions.add(expense);
			}
			for (Income income : incomes) {
				transactions.add(income);
			}
		
		}
		else {
			return new ResponseEntity<Object>("login first" , HttpStatus.BAD_REQUEST);
		}
		return null;
		
	}
	
	

}
