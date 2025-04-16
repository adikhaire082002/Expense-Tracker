package com.tracker.expense.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true , nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(unique = true , nullable = false)
	private long mobile;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	private List<Income> incomes;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Expense> expenses;
	
	private int amount;
	
	public User() {
		
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", mobile=" + mobile + ", incomes=" + incomes
				+ ", expenses=" + expenses + ", amount=" + amount + "]";
	}
	
	
	
	
}
