package com.group.Kudos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.group.Kudos.models.User;
import com.group.Kudos.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository uRepo; 
	
	public User findUserByEmail(String email) {
		return this.uRepo.findByEmail(email);
	}
	
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return this.uRepo.save(user);
	}
	
	public User findUserById(Long id) {
		Optional<User> u = this.uRepo.findById(id);
		if(u.isPresent()) {
			return u.get();
		}else {
			return null; 
		}
	}
	
	public boolean authenticateUser(String email, String password) {
		User user = this.uRepo.findByEmail(email);
		if(user == null) {
			return false; 
		}
			return BCrypt.checkpw(password, user.getPassword());
	}
	
}
