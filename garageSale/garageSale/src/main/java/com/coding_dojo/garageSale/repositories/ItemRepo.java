package com.coding_dojo.garageSale.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.coding_dojo.garageSale.models.Item;
import com.coding_dojo.garageSale.models.User;

@Repository
public interface ItemRepo extends CrudRepository<Item, Long> {
	List<Item> findAll();
	List<Item> findByUser(User user);
}
