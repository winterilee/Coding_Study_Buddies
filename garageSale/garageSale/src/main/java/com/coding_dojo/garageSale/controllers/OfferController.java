package com.coding_dojo.garageSale.controllers;

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

import com.coding_dojo.garageSale.models.Offer;
import com.coding_dojo.garageSale.services.ItemService;
import com.coding_dojo.garageSale.services.OfferService;
import com.coding_dojo.garageSale.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class OfferController {
	@Autowired
	private OfferService offerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/offers/{id}/new_offer")
	public String newOfferPage(@PathVariable Long id, Model model, @ModelAttribute("newOffer") Offer newOffer) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("currentUser", userService.getById(userId));
		model.addAttribute("itemToOffer", itemService.getOne(id));
		return "newOffer.jsp";
	}
	
	@PostMapping("/offers/addOffer")
	public String addOffer(@Valid @ModelAttribute("newOffer") Offer newOffer, BindingResult result, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		Long itemId = newOffer.getItemWithOffers().getId();
		
		if(result.hasErrors()) {
			model.addAttribute("currentUser", userService.getById(userId));
			model.addAttribute("itemToOffer", itemService.getOne(itemId));
			return "newOffer.jsp";
		}
		offerService.create(newOffer);
		return "redirect:/garagesale/" + itemId;
	}
	
	@GetMapping("/offers/{id}/edit_offer")
	public String editOffer(@PathVariable Long id, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		Offer thisOffer = offerService.findById(id);
		model.addAttribute("currentUser", userService.getById(userId));
		model.addAttribute("thisOffer", thisOffer);
		return "editOffer.jsp";
	}
	
	@PutMapping("/offers/{id}/editOffer")
	public String editOfferInDb(@PathVariable Long id, @Valid @ModelAttribute("thisOffer") Offer thisOffer, BindingResult result, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		if (result.hasErrors()) {
			model.addAttribute("currentUser", userService.getById(userId));
			model.addAttribute("thisOffer", thisOffer);
			return "editOffer.jsp";
		}
		offerService.updateOffer(thisOffer);
		return "redirect:/garagesale/" + thisOffer.getItemWithOffers().getId();
	
	}
	
	@DeleteMapping("/offers/{id}/deleteOffer")
	public String deleteOffer(@PathVariable Long id) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		offerService.deleteOffer(id);
		return "redirect:/home";
	}
}
