package com.coding_dojo.garageSale.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coding_dojo.garageSale.models.Offer;
@Repository
public interface OfferRepo extends CrudRepository <Offer, Long> {
	
	List<Offer> findAll();
}
