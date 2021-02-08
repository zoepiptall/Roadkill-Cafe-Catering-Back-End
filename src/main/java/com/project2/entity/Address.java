package com.project2.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name="Address")
public class Address {
	
	@Id
	@Column(name="address_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private int addressId;
	
	@Column(name="street_address", nullable=false)
	private String streetAddress;
	
	@Column(name="city_address", nullable=false)
	private String cityAddress;
	
	@Column(name="zip_address", nullable=false)
	private int zipAddress;
	
	@Column(name="state_address", nullable=false)
	private String stateAddress;
	
	@OneToMany(mappedBy="eventAddress")
	private List<SalesOrder> sOrderListEvent = new ArrayList<>();
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Customer> cList = new ArrayList<>();
	
	public Address() {
		// TODO Auto-generated constructor stub
	}


	public Address(String streetAddress, String cityAddress, int zipAddress, String stateAddress, 
			List<SalesOrder> sOrderListEvent, List<Customer> cList) {
		this.streetAddress = streetAddress;
		this.cityAddress = cityAddress;
		this.zipAddress = zipAddress;
		this.stateAddress = stateAddress;
		this.sOrderListEvent = sOrderListEvent;
		this.cList = cList;
	}


	public Address(String streetAddress, String cityAddress, int zipAddress, String stateAddress,
			Customer customer) {

		super();
		this.streetAddress = streetAddress;
		this.cityAddress = cityAddress;
		this.zipAddress = zipAddress;
		this.stateAddress = stateAddress;

	}


	public Address(int addressId, String streetAddress, String cityAddress, String stateAddress, int zipAddress) {
		super();
		this.addressId = addressId;
		this.streetAddress = streetAddress;
		this.cityAddress = cityAddress;
		this.zipAddress = zipAddress;
		this.stateAddress = stateAddress;
	}


	

}
