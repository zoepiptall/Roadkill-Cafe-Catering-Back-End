package com.project2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.entity.Inventory;
import com.project2.repository.InventoryDao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class InventoryService {

	public InventoryDao inventoryDao;
	
	public List<Inventory> findAll(){
		return inventoryDao.findAll();
	}
	
	public Inventory findByInventoryId(int inventoryId) {
		return inventoryDao.findByItemId(inventoryId);
	}
	
	
	
	
}
