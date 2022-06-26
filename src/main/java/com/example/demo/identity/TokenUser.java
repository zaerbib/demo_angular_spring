package com.example.demo.identity;

import org.springframework.security.core.authority.AuthorityUtils;

import com.example.demo.model.user.User;

public class TokenUser extends org.springframework.security.core.userdetails.User {
	private User user;

	public TokenUser(User user) {
		super(user.getUserId(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public String getRole() {
		return user.getRole().toString();
	}
}
