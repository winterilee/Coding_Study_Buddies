package com.coding_dojo.garageSale.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.coding_dojo.garageSale.models.User;
import com.coding_dojo.garageSale.repositories.UserRepo;
import com.coding_dojo.garageSale.validators.UserValidator;

@Service
public class UserService {
	
//	injecting UserRepo
	@Autowired
	private UserRepo userRepo;
	
//	REGISTRATION / LOGIN
	public User register(User newUser, BindingResult result) {
		Optional<User> potentialUser = this.userRepo.findByEmail(newUser.getEmail());
		
		if(potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "Email is already taken. Please enter another email.");
		}
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "Passwords must match.");
		}
		if(result.hasErrors()) {
			return null;			
		}
		
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		
		return this.userRepo.save(newUser);
	}
	
	public User getById(Long id) {
		return this.userRepo.findById(id).orElse(null);
	}
	
	public User login(UserValidator newLoginObject, BindingResult result) {
		
		Optional<User> potentialUser = this.userRepo.findByEmail(newLoginObject.getEmail());
		
		if(potentialUser.isPresent()) {
			User user = potentialUser.get();
			if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
				result.rejectValue("email", "Matches", "Invalid Credentials.");
			}
			return user;
		} else {
			if (!newLoginObject.getEmail().equals("")) {
				result.rejectValue("email", "Matches", "Invalid Credentials.");				
			}
			return null;
		}
	}
	
//	retrieve one user
	public User findOne(Long id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		else {
			return null;
		}
	}
	
//	retrieve all users
	public List<User> getAll() {
		return userRepo.findAll();
	}
}
