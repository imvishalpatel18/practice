/*
 * Created on Mar 6, 2020
 *
 * $Id: CodeTemplates.xml,v 1.1 2012/11/29 19:54:11 adets Exp $
 * 
 * Copyright 2020 InfoDesk, Inc. All rights reserved.
 */
package com.orange.ftgo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.orange.ftgo.entity.Customer;
import com.orange.ftgo.service.CustomerService;

@RestController
public class UserProfileController {
	@Autowired
	private CustomerService customerService;

	@GetMapping(value = "/api/users/user/{id}", produces = "application/json")
	public Customer getUserDetail(@PathVariable Long id) {
		Optional<Customer> customerMayBe = customerService.findById(id);
		if (customerMayBe.isPresent()) {
			return customerMayBe.get();	
		} else {
			return null;
		}
	}
}
