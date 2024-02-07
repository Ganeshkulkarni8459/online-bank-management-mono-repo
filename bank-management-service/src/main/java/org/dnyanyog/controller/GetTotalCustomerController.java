package org.dnyanyog.controller;

import java.util.List;

import org.dnyanyog.entity.CustomerResgistration;
import org.dnyanyog.service.GetTotalCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetTotalCustomerController {
	
	@Autowired
	GetTotalCustomerService getTotalCustomerService;
	
	@GetMapping(path = "/api/v1/admin/total/customers")
	public List<CustomerResgistration> getAllUser() {
		return getTotalCustomerService.getAllUser();
	}
}
