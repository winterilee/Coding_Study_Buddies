package com.coding_dojo.garageSale.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coding_dojo.garageSale.models.Item;
import com.coding_dojo.garageSale.repositories.ItemRepo;

@Service
public class ItemService {

//	inject ItemRepo
	@Autowired
	private ItemRepo itemRepo;
	
//	create item
	public Item create(Item newItem) {
		return itemRepo.save(newItem);
	}
	
//	retrieve item
	public Item getOne(Long id) {
		Optional<Item> item = itemRepo.findById(id);
		if (item.isPresent()) {
			return item.get();
		}
		else {
			return null;
		}
	}
	
//	retrieve all items
	public List<Item> allItems() {
		return itemRepo.findAll();
	}
	
//	update item
	public Item update(Item editedItem) {
		return itemRepo.save(editedItem);
	}
	
//	delete item
	public void delete(Long id) {
		itemRepo.deleteById(id);
	}
}
