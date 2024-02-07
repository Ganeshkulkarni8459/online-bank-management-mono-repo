package org.dnyanyog.controller;

import org.dnyanyog.dto.request.CustomerWithdrawAmountRequest;
import org.dnyanyog.dto.response.CustomerWithdrawAmountResponse;
import org.dnyanyog.service.CustomerWithdrawAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerWithdrawAmountController {
	
	@Autowired
	CustomerWithdrawAmountService customerWithdrawAmountService;
	
	@PostMapping(path = "/api/v1/customer/withdraw/balance", consumes = { "application/json", "application/xml" }, produces = {"application/json", "application/xml" })
	public CustomerWithdrawAmountResponse depositAmount( @RequestBody CustomerWithdrawAmountRequest request) {
		return customerWithdrawAmountService.withdrawAmount(request);
	}
}
