package com.project2.entity;

import java.sql.Blob;

import java.util.ArrayList;
import java.util.List;

import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table (name = "Inventory")
public class Inventory {
	
	@Id
	@Column(name="item_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private int itemId;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="sales_price")
	private double salesPrice;
	
	@Column(name="description")
	private String description;
	
	@Column(name="menu_img")
	private String menuImg;
	
	/*
	 * @OneToMany(mappedBy="inventory", fetch=FetchType.EAGER) private
	 * List<InvoiceItem> itemList = new ArrayList<>();
	 */
	
	public Inventory(double salesPrice, String description, String menuImg) {
		super();
		this.salesPrice = salesPrice;
		this.description = description;
		this.menuImg = menuImg;
	}

//	public Inventory(int itemId, String productName, double salesPrice, String description, String menuImg) {
//		super();
//		this.itemId = itemId;
//		this.productName = productName;
//		this.salesPrice = salesPrice;
//		this.description = description;
//		this.menuImg = menuImg;
//	}
	
	

}
