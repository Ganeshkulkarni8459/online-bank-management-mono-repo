package org.dnyanyog.controller;

import org.dnyanyog.dto.response.AdminCanDeleteTheCustomersResponse;
import org.dnyanyog.service.AdminCanDeleteTheCustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminCanDeleteTheCustomerController {

	@Autowired
	AdminCanDeleteTheCustomersService adminCanDeleteTheCustomersService;

	@DeleteMapping(path = "/api/v1/admin/delete/customer/{customerId}", consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	public AdminCanDeleteTheCustomersResponse deleteCustomer(@PathVariable Long customerId) {
		return adminCanDeleteTheCustomersService.deleteCustomer(customerId);
	}

}
