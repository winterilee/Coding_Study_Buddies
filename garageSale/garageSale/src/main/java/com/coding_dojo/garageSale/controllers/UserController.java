package com.coding_dojo.garageSale.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coding_dojo.garageSale.models.User;
import com.coding_dojo.garageSale.services.UserService;
import com.coding_dojo.garageSale.validators.UserValidator;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public String index(@ModelAttribute("newUser")User newUser, Model viewModel) {
		viewModel.addAttribute("loginUser", new UserValidator());
		
		return "index.jsp";
	}
	
	@PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model viewModel, HttpSession session) {
        
		User newestUser = this.userService.register(newUser, result);
        if(result.hasErrors()) {
        	viewModel.addAttribute("loginUser", new UserValidator());
            return "index.jsp";
        }
        
        session.setAttribute("userId", newestUser.getId());
        
        return "redirect:/home";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("loginUser") UserValidator newLogin,
			BindingResult result, Model viewModel, HttpSession session) {
		
		User user = this.userService.login(newLogin, result);
		if(result.hasErrors()) {
			viewModel.addAttribute("newUser", new User());
			return "index.jsp";
		}
		
		session.setAttribute("userId", user.getId());
		
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String dashboard(Model viewModel, HttpSession session) {
		Long currentUserId = (Long) session.getAttribute("userId");
		if (currentUserId == null) {
			return "redirect:/";
		}
		User currentUser = this.userService.getById(currentUserId);
		viewModel.addAttribute("currentUser", currentUser);
		
		return "home.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
