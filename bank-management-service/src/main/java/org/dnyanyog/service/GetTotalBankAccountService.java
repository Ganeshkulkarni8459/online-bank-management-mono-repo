package org.dnyanyog.service;


import java.util.List;
import java.util.Optional;

import org.dnyanyog.dto.response.AdminCanGetTotalBankAccountResponse;
import org.dnyanyog.entity.Account;
import org.dnyanyog.entity.CustomerResgistration;
import org.dnyanyog.entity.TotalAccounts;
import org.dnyanyog.repo.AddAccountServiceRepository;
import org.dnyanyog.repo.CustomerResgistrationRepository;
import org.dnyanyog.repo.GetTotalBankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetTotalBankAccountService {
	
	@Autowired
	GetTotalBankAccountRepository repo;

	public List<TotalAccounts> getAllAccount(){
		return repo.findAll();
	}
}
