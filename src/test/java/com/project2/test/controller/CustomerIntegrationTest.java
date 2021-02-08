package com.project2.test.controller;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project2.entity.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerIntegrationTest {

	private MockMvc mock;

	private Customer customer;

	@Autowired
	private WebApplicationContext wac;

	@BeforeEach
	public void initBeforeTest() throws Exception {
		customer = new Customer("test1", "asdasdasd", "Patrick", "Wilson", "test@test.com");
		mock = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void postFoodTest() throws Exception {
		mock.perform(post("/users/create").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(customer))).andExpect(status().isCreated())
				.andExpect(jsonPath("$").value("Resource Created"));
	}

	@Test
	public void loginTest() throws Exception {
		mock.perform(delete("/users/login", customer.getUsername())).andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Login successful"));
	}

	@Test
	public void recoverAccountTest() throws Exception {
		mock.perform(get("/users/recover", customer.getEmail())).andExpect(status().isOk())
				.andExpect(jsonPath("$.email", is(customer.getEmail())));
	}

//	@Test
//	public void putNotAllowedTest() throws Exception {
//		mock.perform(put("/foods/validate")).andDo(print()).andExpect(status().isMethodNotAllowed());
//	}

}
