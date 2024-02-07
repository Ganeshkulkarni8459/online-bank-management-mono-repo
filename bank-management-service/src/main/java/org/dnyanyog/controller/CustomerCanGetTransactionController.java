package org.dnyanyog.controller;

import java.util.List;

import org.dnyanyog.dto.request.CustomerCanGetTransactionsRequest;
import org.dnyanyog.dto.request.CustomerLoginRequest;
import org.dnyanyog.entity.Transactions;
import org.dnyanyog.service.CustomerCanGetTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transaction;

@RestController
public class CustomerCanGetTransactionController {

	@Autowired
	CustomerCanGetTransactionsService customerCanGetTransactionsService;

	@GetMapping("/api/v1/customer/get/transactions/{customerId}")
	public ResponseEntity<List<Transactions>> getTransactionsByCustomerId(@PathVariable Long customerId) {
		List<Transactions> transactions = customerCanGetTransactionsService.getTransactionsByCustomerId(customerId);
		return ResponseEntity.ok(transactions);
	}
}
