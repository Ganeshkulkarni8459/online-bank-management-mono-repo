package org.dnyanyog.controller;


import org.dnyanyog.dto.request.CustomerLoginRequest;
import org.dnyanyog.dto.response.CustomerLoginResponse;
import org.dnyanyog.service.CustomerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerLoginController {
	
	@Autowired
	CustomerLoginService customerLoginService;
	
	@PostMapping(path = "/api/v1/customer/login", consumes = { "application/json", "application/xml" }, produces = {"application/json", "application/xml" })
	public CustomerLoginResponse loginCustomer(@RequestBody CustomerLoginRequest customerLoginRequest) {
		return customerLoginService.login(customerLoginRequest);

	}

}
