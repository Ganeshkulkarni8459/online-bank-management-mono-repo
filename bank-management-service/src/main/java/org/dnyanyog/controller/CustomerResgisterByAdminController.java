package org.dnyanyog.controller;

import org.dnyanyog.dto.request.CustomerResgisterByAdminRequest;
import org.dnyanyog.dto.response.CustomerResgisterByAdminResponse;
import org.dnyanyog.service.CustomerResgistrationByAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerResgisterByAdminController {
	
	@Autowired
	CustomerResgistrationByAdminService service;
	
	@PostMapping(path = "/api/admin/add/customerbyadmin")
	public CustomerResgisterByAdminResponse addCustomer(@RequestBody CustomerResgisterByAdminRequest request) {
		
		return service.resgister(request);
	}	
}
