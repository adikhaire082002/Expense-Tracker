package com.tracker.expense.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.tracker.expense.entity.User;
import com.tracker.expense.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;

	public User addUser(User user) {
		try {
			User save = repository.save(user);
			return save;
		} catch (Exception e) {
			return null;
		}
	}

	public User findByUser(String username) {
		try {
			User user = repository.findByUsername(username);
			return user;
		} catch (Exception e) {
			return null;
		}
		
	}

	

	public User findByUsernameAndPassword(String username, String password) {
		try {
			User byUser = repository.findByUsernameAndPassword(username,password);
			return byUser;
		} catch (Exception e) {
			return null;
		}
		
	}

	public boolean deleteUser(String username) {
		User byUser = findByUser(username);
	try {
		repository.delete(byUser);
		return true;
	} catch (Exception e) {
		// TODO: handle exception
	}
	return false;	
	}


}
