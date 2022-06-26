package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.product.Product;

@Transactional
public interface ProductRepo extends JpaRepository<Product, Integer> {
	public List<Product> findAll();
    public Page<Product> findAll(Pageable p);
    Optional<Product> findOneById(Integer id);
}
