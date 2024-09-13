package com.movie.Moviebackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.Moviebackend.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> 
{
    // You can define custom query methods here if needed
	 List<Expense> findByEmail(String email); // Custom query method to find expenses by email
}
