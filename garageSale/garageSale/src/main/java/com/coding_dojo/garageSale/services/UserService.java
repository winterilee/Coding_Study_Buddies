package com.coding_dojo.garageSale.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coding_dojo.garageSale.models.User;
import com.coding_dojo.garageSale.repositories.UserRepo;

@Service
public class UserService {
	
//	injecting UserRepo
	@Autowired
	private UserRepo userRepo;
	
//	REGISTRATION / LOGIN --- Winter, I'll leave these to you!
	public User register() {}
	
	public User login() {}
	
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
