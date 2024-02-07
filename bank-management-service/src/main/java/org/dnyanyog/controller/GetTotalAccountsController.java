package org.dnyanyog.controller;

import java.util.List;

import org.dnyanyog.entity.TotalAccounts;
import org.dnyanyog.service.GetTotalBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetTotalAccountsController {
	
	@Autowired
	GetTotalBankAccountService service;
	
	@GetMapping(path = "/api/v1/admin/total/account")
	public List<TotalAccounts> getAllAccount() {
		return service.getAllAccount();
	}
	

}
