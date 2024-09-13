package com.movie.Moviebackend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.movie.Moviebackend.model.Expense;
import com.movie.Moviebackend.repository.ExpenseRepository;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    // Create a new expense
    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        try {
            Expense savedExpense = expenseRepository.save(expense);
            return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all expenses
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        try {
            List<Expense> expenses = expenseRepository.findAll();
            return new ResponseEntity<>(expenses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get an expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable("id") Long id) {
        try {
            Expense expense = expenseRepository.findById(id).orElse(null);
            if (expense != null) {
                return new ResponseEntity<>(expense, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an expense
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable("id") Long id, @RequestBody Expense expenseDetails) {
        try {
            Expense expense = expenseRepository.findById(id).orElse(null);
            if (expense != null) {
                expense.setEmail(expenseDetails.getEmail());
                expense.setCategory(expenseDetails.getCategory());
                expense.setDescription(expenseDetails.getDescription());
                expense.setAmount(expenseDetails.getAmount());
                Expense updatedExpense = expenseRepository.save(expense);
                return new ResponseEntity<>(updatedExpense, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete an expense
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteExpense(@PathVariable("id") Long id) {
        try {
            expenseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Get expenses by email
    @GetMapping("/email/{email}")
    public ResponseEntity<List<Expense>> getExpensesByEmail(@PathVariable("email") String email) {
        try {
            List<Expense> expenses = expenseRepository.findByEmail(email);
            if (!expenses.isEmpty()) {
                return new ResponseEntity<>(expenses, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
