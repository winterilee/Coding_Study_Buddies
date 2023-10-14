package com.groupmhl.garagesale.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.groupmhl.garagesale.models.User;
import com.groupmhl.garagesale.repositories.UserRepository;
import com.groupmhl.garagesale.validators.UserValidator;

@Service
public class UserService {
	@Autowired
	private UserRepository uRepo;
	
	public User register(User newUser, BindingResult result) {
		Optional<User> potentialUser = this.uRepo.findByEmail(newUser.getEmail());
		
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
		
		return this.uRepo.save(newUser);
	}
	
	public User getById(Long id) {
		return this.uRepo.findById(id).orElse(null);
	}
	
	public User login(UserValidator newLoginObject, BindingResult result) {
		
		Optional<User> potentialUser = this.uRepo.findByEmail(newLoginObject.getEmail());
		
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
}
