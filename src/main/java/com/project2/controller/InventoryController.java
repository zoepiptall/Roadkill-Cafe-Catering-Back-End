package com.project2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project2.entity.Inventory;
import com.project2.service.InventoryService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(value="/inventory")
@AllArgsConstructor(onConstructor=@__(@Autowired)) 
@NoArgsConstructor
public class InventoryController {
	
	private InventoryService inventoryServ;
	
	@GetMapping("/all")
	public ResponseEntity<List<Inventory>> getAll() {
		List<Inventory> iList = inventoryServ.findAll();
		return new ResponseEntity<>(iList, HttpStatus.OK);
	}

}
