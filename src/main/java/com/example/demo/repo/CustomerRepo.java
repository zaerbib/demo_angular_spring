package com.example.demo.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.customer.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	public List<Customer> findAll();
    public Page<Customer> findAll(Pageable p);
    
	// Customer save(Customer c);
    // void delete(Customer c);
    // void delete(Integer id);
    // boolean exists(Integer id);
}
