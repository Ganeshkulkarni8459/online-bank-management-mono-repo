package org.dnyanyog.controller;

import org.dnyanyog.dto.request.AddAccountRequest;
import org.dnyanyog.dto.response.AddAccountResponse;
import org.dnyanyog.service.AddAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddAccountController {
	
	@Autowired
	AddAccountService service;
	
	@PostMapping(path = "/api/v1/admin/add/Account/{customerId}", consumes = { "application/json", "application/xml" }, produces = {
			"application/json", "application/xml" })
	public AddAccountResponse addAccount(@PathVariable Long customerId,@RequestBody AddAccountRequest addAccountRequest) {
		
		return service.addAccount(customerId,addAccountRequest);
	}
}




