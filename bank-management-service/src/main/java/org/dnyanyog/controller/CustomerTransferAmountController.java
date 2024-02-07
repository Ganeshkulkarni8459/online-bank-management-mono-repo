package org.dnyanyog.controller;

import org.dnyanyog.dto.request.CustomerTranferAmountRequest;
import org.dnyanyog.dto.request.CustomerWithdrawAmountRequest;
import org.dnyanyog.dto.response.CustomerTransferAmountResponse;
import org.dnyanyog.service.CustomerTransferAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerTransferAmountController {
	
	@Autowired
	CustomerTransferAmountService customerTransferAmountService;
	
	@PostMapping(path = "/api/v1/customer/transfer/balance", consumes = { "application/json", "application/xml" }, produces = {"application/json", "application/xml" })
	public CustomerTransferAmountResponse transferAmount( @RequestBody CustomerTranferAmountRequest request) {
		return customerTransferAmountService.transferAmount(request);

	}


}
