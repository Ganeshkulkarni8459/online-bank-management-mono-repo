package org.dnyanyog.controller;

import java.util.List;

import org.dnyanyog.entity.Transactions;
import org.dnyanyog.service.AdminCanGetTotalTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminCanGetTotalTransactionController {
	
	@Autowired
	AdminCanGetTotalTransactionService adminCanGetTotalTransactionService;
	
	@GetMapping(path = "/api/v1/admin/get/total/transactions")
	public List<Transactions> getAllTransactions() {
		return adminCanGetTotalTransactionService.getAllTransactions();
	}
}
