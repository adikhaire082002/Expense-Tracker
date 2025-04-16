package com.tracker.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tracker.expense.entity.User;
import com.tracker.expense.service.UserService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@SessionAttributes("user")
public class AuthController {
	
	@Autowired
	private UserService service;
	
	@GetMapping(path = "/login")
	private ResponseEntity<Object> login(@RequestParam String username , @RequestParam String password ,HttpSession httpSession  ){
		
		User byUser = service.findByUser(username);
		if(byUser != null) {
			User byPassword = service.findByUsernameAndPassword(username,password);
			if(byPassword != null) {
				httpSession.setAttribute("user",byPassword);
				return new ResponseEntity<Object>("Login Succesfully",HttpStatus.ACCEPTED);
				
			}
			else {
				return new ResponseEntity<Object>("Wrong Password",HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return new ResponseEntity<Object>("Username not found",HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/logout")
	private ResponseEntity<Object> logout(HttpSession httpSession) {
		User user = (User)httpSession.getAttribute("user");
		httpSession.setAttribute("user", null);
		return new ResponseEntity<Object>("Log out Succesful", HttpStatus.ACCEPTED);
		
	}
	
	
	
}
