package com.coding_dojo.garageSale.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coding_dojo.garageSale.models.Item;
import com.coding_dojo.garageSale.models.User;
import com.coding_dojo.garageSale.services.ItemService;
import com.coding_dojo.garageSale.services.UserService;
import com.coding_dojo.garageSale.validators.UserValidator;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private UserService userService;
	
//	display login/registration page
	@GetMapping("")
	public String index(
			@ModelAttribute("newUser") User newUser, 
			Model viewModel) {
		viewModel.addAttribute("loginUser", new UserValidator());
		return "index.jsp";
	}
	
//	register user
	@PostMapping("/register")
    public String register(
    		@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, 
            Model viewModel, 
            HttpSession session) {
        
		User newestUser = this.userService.register(newUser, result);
        if(result.hasErrors()) {
        	viewModel.addAttribute("loginUser", new UserValidator());
            return "index.jsp";
        }
        
        session.setAttribute("userId", newestUser.getId());       
        return "redirect:/home";
	}
	
//	login user
	@PostMapping("/login")
	public String login(
			@Valid @ModelAttribute("loginUser") UserValidator newLogin,
			BindingResult result, 
			Model viewModel, 
			HttpSession session) {
		
		User user = this.userService.login(newLogin, result);
		if(result.hasErrors()) {
			viewModel.addAttribute("newUser", new User());
			return "index.jsp";
		}
		
		session.setAttribute("userId", user.getId());		
		return "redirect:/home";
	}
	
//	display user home page
	@GetMapping("/home")
	public String dashboard(
			Model viewModel, 
			HttpSession session) {
		Long currentUserId = (Long) session.getAttribute("userId");
		if (currentUserId == null) {
			return "redirect:/";
		}
		List<Item> items = itemService.allItems();
		User currentUser = this.userService.getById(currentUserId);
		viewModel.addAttribute("items", items);
		viewModel.addAttribute("currentUser", currentUser);	
		return "home.jsp";
	}
	
//	log user out
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
//	display new item form
	@GetMapping("/garagesale/new")
	public String newItemForm(
			HttpSession session, 
			@ModelAttribute("item") Item newItem,
			Model viewModel) {
		Long currentUserId = (Long) session.getAttribute("userId");
		if (currentUserId == null) {
			return "redirect:/";
		}
		User currentUser = this.userService.getById(currentUserId);
		viewModel.addAttribute("item", new Item());
		viewModel.addAttribute("user", currentUser);
		return "newItem.jsp";
	}
	
//	posting form data from new item form
	@PostMapping("/garagesale/new/process")
	public String addItem(
			HttpSession session,
			@Valid @ModelAttribute("item") Item newItem,
			BindingResult result,
			Model viewModel) {
		if (result.hasErrors()) {
			return "newItem.jsp";
		}
		itemService.create(newItem);
		return "redirect:/home";
	}
	
//	display edit item form
	@GetMapping("/garagesale/{id}/edit")
	public String editItemForm(
			@PathVariable("id") Long id,
			HttpSession session,
			@ModelAttribute("item") Item item,
			Model viewModel) {
		Long currentUserId = (Long) session.getAttribute("userId");
		if (currentUserId == null) {
			return "redirect:/";
		}
		Item currentItem = itemService.getOne(id);
		User currentUser = this.userService.getById(currentUserId);
		viewModel.addAttribute("user", currentUser);
		viewModel.addAttribute("item", currentItem);
		return "editItem.jsp";
	}
	
//	put request for form data from edit item form
	@PutMapping("/garagesale/{id}/edit/process")
	public String editItem(
			@PathVariable("id") Long id,
			HttpSession session,
			@Valid @ModelAttribute("item") Item item,
			BindingResult result,
			Model viewModel) {
		if (result.hasErrors()) {
			return "editItem.jsp";
		}
		itemService.update(item);
		return "redirect:/home";
	}
	
	
//	posting item deletion
	@DeleteMapping("/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		itemService.delete(id);
		return "redirect:/home";
	}
}
