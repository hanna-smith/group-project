package com.group.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.group.models.User;
import com.group.repositories.RoleRepository;
import com.group.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository uRepo; 
	@Autowired
	private RoleRepository rRepo; 
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	public User findUserByEmail(String email) {
		return this.uRepo.findByEmail(email);
	}
	
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return this.uRepo.save(user);
	}
	
	   public void saveWithUserRole(User user) {
	        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        user.setRoles(rRepo.findByName("ROLE_USER"));
	        uRepo.save(user);
	    }
	   
	    public void saveUserWithBusinessRole(User user) {
	        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        user.setRoles(rRepo.findByName("ROLE_BUSINESS"));
	        uRepo.save(user);
	    }  
	    
	    public User findByUsername(String username) {
	        return uRepo.findUserByUsername(username);
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