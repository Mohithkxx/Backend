package com.movie.Moviebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.movie.Moviebackend.model.Customer;
import com.movie.Moviebackend.repository.CustomerRepository;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // POST method to store new customer data
    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody Customer customer) {
        if (customerRepository.findByUsername(customer.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        if (customerRepository.findByEmail(customer.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        customerRepository.save(customer);
        return ResponseEntity.ok("Registration successful");
    }

    // GET method to retrieve all customers
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok(customers);
    }

    // POST method for login using email and password
    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        
        Customer customer = customerRepository.findByEmail(email);

        if (customer == null) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        if (!customer.getPassword().equals(password)) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        return ResponseEntity.ok("Login successful");
    }
}

// DTO for login request
class LoginRequest {
    private String email;
    private String password;

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
