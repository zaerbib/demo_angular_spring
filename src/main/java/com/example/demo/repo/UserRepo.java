package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.user.User;

public interface UserRepo extends JpaRepository<User, Long> {
	Optional<User> findOneByUserId(String userId);
    Optional<User> findOneByUserIdAndPassword(String userId, String password);
}
