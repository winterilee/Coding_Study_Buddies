package com.coding_dojo.garageSale.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding_dojo.garageSale.models.Offer;
import com.coding_dojo.garageSale.repositories.OfferRepo;

@Service
public class OfferService {
	@Autowired
	private OfferRepo offerRepo;
	
	public List <Offer> findAll() {
		return offerRepo.findAll();
	}
	
	public Offer findById(Long offerId) {
		Optional <Offer> optionalOffer = offerRepo.findById(offerId);
		if(optionalOffer.isPresent()) {
			return optionalOffer.get();
		}
		return null;
	}
	
	public Offer create(Offer offer) {
		return offerRepo.save(offer);
	}
	
	public Offer updateOffer(Offer updatedOffer) {
		return offerRepo.save(updatedOffer);
	}
	
	public void deleteOffer(Long offerId) {
		offerRepo.deleteById(offerId);
	}
}

