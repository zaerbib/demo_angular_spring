package com.example.demo.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.order.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {
	public List<Order> findAll();
    public Page<Order> findAll(Pageable p);
}
