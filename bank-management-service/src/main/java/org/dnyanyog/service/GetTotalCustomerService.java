package org.dnyanyog.service;


import java.util.List;

import org.dnyanyog.entity.CustomerResgistration;
import org.dnyanyog.repo.GetTotalCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetTotalCustomerService {
	
	@Autowired
	GetTotalCustomerRepository repo;

	public List<CustomerResgistration> getAllUser(){
		return repo.findAll();
	}
	
	
	


}
