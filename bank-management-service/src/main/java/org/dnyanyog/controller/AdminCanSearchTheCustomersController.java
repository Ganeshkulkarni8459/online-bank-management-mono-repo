package org.dnyanyog.controller;

import org.dnyanyog.dto.response.AdminCanSearchTheCustomersResponse;
import org.dnyanyog.service.AdminCanSearchTheCustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminCanSearchTheCustomersController {
	
	@Autowired
	AdminCanSearchTheCustomersService adminCanSearchTheCustomersService;
	
	@GetMapping(path = "/api/v1/admin/search/customer/{customerId}")
	public AdminCanSearchTheCustomersResponse getSingleCustomer(@PathVariable Long customerId) {
		return adminCanSearchTheCustomersService.getSingleCustomer(customerId);

	}
}
