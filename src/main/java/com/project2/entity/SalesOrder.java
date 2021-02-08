package com.project2.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//need these so that hibernate knows this class is mapping a table to the database
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Sales_Order")
public class SalesOrder {

	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private int orderId;

	@Column(name = "date_submitted")
	private Timestamp dateSubmitted;

	@Column(name = "event_date")
	private Timestamp eventDate;

	@Column(name = "order_status")
	private String status;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_fk")
	private Customer customer;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "event_address_fk")
	private Address eventAddress;

	
	  //@OneToMany(mappedBy="salesOrder")
	 
    @OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinColumn(name="item_list_fk") 
    private List<InvoiceItem> itemList = new ArrayList<>();
	 

	public SalesOrder() {
		// TODO Auto-generated constructor stub
	}

	public SalesOrder(Timestamp dateSubmitted, Timestamp eventDate, String status, Customer customer,
			Address eventAddress) {

		super();
		this.dateSubmitted = dateSubmitted;
		this.eventDate = eventDate;
		this.status = status;
		this.customer = customer;
		this.eventAddress = eventAddress;
	}

	public SalesOrder(Customer customer) {
		super();
		this.customer = customer;
		this.status = "Pending";
	}

	public Timestamp getDateSubmitted() {
		return dateSubmitted;
	}
	
	

	@Override
	public String toString() {
		return "SalesOrder [orderId=" + orderId + ", dateSubmitted=" + dateSubmitted + ", eventDate=" + eventDate
				+ ", status=" + status + ", eventAddress=" + eventAddress;
	}

	public SalesOrder(int orderId, String status, Customer customer) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.customer = customer;
	}

}
