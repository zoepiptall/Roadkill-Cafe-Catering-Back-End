package com.project2.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project2.entity.Inventory;
import com.project2.repository.InventoryDao;
import com.project2.service.InventoryService;

@SpringBootTest
public class InventoryServiceTest {
	
	@Mock
	private InventoryDao iDao;
	
	@InjectMocks
	private InventoryService iServ;
	
//	@Mock
	private Inventory inventory;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		iServ = new InventoryService(iDao);
		inventory = new Inventory(1, "The Full Spread", 15.00, "Why pick one meat when you can have them all?", "image location");
		List<Inventory> invList = new ArrayList<Inventory>();
		invList.add(inventory);
//		System.out.println(invList);
		when(iDao.findByItemId(1)).thenReturn(inventory);
		when(iDao.findAll()).thenReturn(invList);
	}
	
	@Test
	public void findByInventoryIdTest (){
		Inventory iFound = iServ.findByInventoryId(1);
		assertEquals(iFound, inventory);
	}
	
	@Test
	public void findAll() {
		List<Inventory> iList = new ArrayList<Inventory>();
		iList.add(inventory);
		assertEquals(iServ.findAll(), iList); 
	}
	

}
