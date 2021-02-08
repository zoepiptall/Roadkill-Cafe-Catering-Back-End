package com.project2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project2.entity.Inventory;

public interface InventoryDao extends JpaRepository<Inventory, Integer>{
	
	Inventory findByItemId(int itemId);
	List<Inventory> findAll();
}
