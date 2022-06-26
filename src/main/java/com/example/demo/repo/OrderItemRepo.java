package com.example.demo.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.order.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {
	public List<OrderItem> findAll();
    public Page<OrderItem> findAll(Pageable p);
}
