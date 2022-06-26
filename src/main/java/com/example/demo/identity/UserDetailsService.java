package com.example.demo.identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.user.User;
import com.example.demo.repo.UserRepo;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userRepo.findOneByUserId(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		TokenUser currentUser;
		if (user.isActive() == true){
			currentUser = new TokenUser(user);
		}
		else{
			throw new DisabledException("User is not activated (Disabled User)");
			//If pending activation return a disabled user
			//currentUser = new TokenUser(user, false);
		}
		detailsChecker.check(currentUser);
		return currentUser;
	}

}
