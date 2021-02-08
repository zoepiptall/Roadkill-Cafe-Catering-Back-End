package com.project2.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="Invoice_Item")
public class InvoiceItem {
	
	@Id
	@Column(name="invoice_item_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private int invoiceItemId;
	
	@Column(name="quantity")
	private int quantity;
	
	@ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinColumn(name="inventory_fk")
	private Inventory inventory;
	
	
	/*
	 * @ManyToOne(mappedBy="itemList", fetch=FetchType.EAGER) private SalesOrder
	 * salesOrder;
	 */
	

	public InvoiceItem(int quantity, Inventory inventory) {
		super();
		this.quantity = quantity;
		this.inventory = inventory;
	}
	
	
	public InvoiceItem(Inventory inventory) {
		super();
		this.inventory = inventory;
	}
	

	
	
	
}
