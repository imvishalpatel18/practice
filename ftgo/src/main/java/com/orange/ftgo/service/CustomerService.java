/*
 * Created on Mar 6, 2020
 *
 * $Id: CodeTemplates.xml,v 1.1 2012/11/29 19:54:11 adets Exp $
 * 
 * Copyright 2020 InfoDesk, Inc. All rights reserved.
 */
package com.orange.ftgo.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;

import com.orange.ftgo.entity.Customer;

public interface CustomerService {

	 String login(String username, String password);
	 
	 Optional<User> findByToken(String token);
	 
	 Optional<Customer> findById(Long id);
}
