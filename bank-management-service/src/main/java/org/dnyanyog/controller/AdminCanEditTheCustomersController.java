package org.dnyanyog.controller;

import org.dnyanyog.dto.request.AdminCanEditCustomerRequest;
import org.dnyanyog.dto.response.AdminCanEditTheCustomersResponse;
import org.dnyanyog.entity.CustomerResgistration;
import org.dnyanyog.service.AdminCanEditTheCustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminCanEditTheCustomersController {
	
	@Autowired
	AdminCanEditTheCustomersService adminCanEditTheCustomersRepository;
	
	@PostMapping(path = "/auth/v1/admin/update/customer/{customerId}")
	public AdminCanEditTheCustomersResponse updateCustomer(@PathVariable Long customerId, @RequestBody AdminCanEditCustomerRequest request) {
		return adminCanEditTheCustomersRepository.updateCustomer(customerId, request);

	}
}
