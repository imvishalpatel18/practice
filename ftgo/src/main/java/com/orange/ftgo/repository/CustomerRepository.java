/*
 * Created on Mar 6, 2020
 *
 * $Id: CodeTemplates.xml,v 1.1 2012/11/29 19:54:11 adets Exp $
 * 
 * Copyright 2020 InfoDesk, Inc. All rights reserved.
 */
package com.orange.ftgo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.orange.ftgo.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query(value = "SELECT u FROM Customer u where u.username = ?1 and u.password = ?2 ")
    Optional<Customer> login(String username,String password);
    
    Optional<Customer> findByToken(String token);
}
