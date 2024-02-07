package org.dnyanyog.controller;

import org.dnyanyog.dto.request.CustomerDepositAmountRequest;
import org.dnyanyog.dto.response.CustomerDepositAmountResponse;
import org.dnyanyog.service.CustomerDepositAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerDepositAmountController {
	
	@Autowired
	CustomerDepositAccountService customerDepositAmountService;
	
	@PostMapping(path = "/api/v1/customer/add/balance", consumes = { "application/json", "application/xml" }, produces = {"application/json", "application/xml" })
	public CustomerDepositAmountResponse depositAmount( @RequestBody CustomerDepositAmountRequest request) {
		return customerDepositAmountService.depositAmount(request);

	}
}
