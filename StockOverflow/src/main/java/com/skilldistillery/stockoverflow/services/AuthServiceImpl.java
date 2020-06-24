package com.skilldistillery.stockoverflow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.stockoverflow.entities.Role;
import com.skilldistillery.stockoverflow.entities.User;
import com.skilldistillery.stockoverflow.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UserRepository userRepo;

	@Override
	public User register(User user) {
		String encodedPW = encoder.encode(user.getPassword());
		user.setPassword(encodedPW); // only persist encoded password

		// set other fields to default values
		// set the enabled field of the object to true
		// set the role field of the object to "standard"
		user.setEnabled(true);
		user.setRole(Role.STANDARD);
		userRepo.saveAndFlush(user);
		return user;
	}

}
