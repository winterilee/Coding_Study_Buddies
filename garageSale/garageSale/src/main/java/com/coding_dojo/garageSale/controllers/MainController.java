package com.coding_dojo.garageSale.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.coding_dojo.garageSale.services.ItemService;
import com.coding_dojo.garageSale.services.UserService;

@Controller
public class MainController {
	
//	inject UserService and ItemService
	@Autowired
	private UserService userService;
	@Autowired
	private ItemService itemService;
	
}

// TESTING TESTING TESTING
