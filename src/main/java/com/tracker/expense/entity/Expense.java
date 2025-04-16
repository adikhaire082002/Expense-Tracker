package com.tracker.expense.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Expense {
	
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	@Id
	private int id;
	
	private int amount;
	
	private String category;
	
	@Temporal(TemporalType.DATE) // Stores only the date part in DB
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // Ensures correct JSON format
	private Date date;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;

	public Expense() {
		// TODO Auto-generated constructor stub
	}
}
