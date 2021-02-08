package com.project2.entity;

import java.util.ArrayList;
import java.util.List;

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
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table (name = "Customer")
public class Customer {
	
	@Id
	@Column(name="customer_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private int customerId;

	@Column(name="username", nullable=false, unique=true)
	private String username;

	@Column(name="password", nullable=false)
	private String password;

	@Column(name="first_name", nullable=false)
	private String firstName;

	@Column(name="last_name", nullable=false)
	private String lastName;

	@Column(name="email", nullable=false, unique=true)
	private String email;
	
	@Column(name="phone_number", nullable=false, unique=true)
	private String phoneNumber;
	
	@Column(name="salt", nullable=false)
	private String salt;
	
	/*
	 * @OneToMany(mappedBy="customer", fetch=FetchType.EAGER) private
	 * List<SalesOrder> sOrderList = new ArrayList<>();
	 */
	

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Customer(String username, String password, String firstName, String lastName, String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public Customer(String username, String password, String firstName, String lastName, String email,
			String phoneNumber) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}


	public Customer(int customerId, String username, String password, String firstName, String lastName, String email,
			String phoneNumber) {
		super();
		this.customerId = customerId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
}