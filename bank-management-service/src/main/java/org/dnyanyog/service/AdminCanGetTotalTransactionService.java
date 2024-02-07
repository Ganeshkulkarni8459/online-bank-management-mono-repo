package org.dnyanyog.service;

import java.util.List;

import org.dnyanyog.entity.Transactions;
import org.dnyanyog.repo.AdminCanGetTotalTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminCanGetTotalTransactionService {
	
	@Autowired
	AdminCanGetTotalTransactionRepository repo;
	
	public List<Transactions> getAllTransactions(){
		return repo.findAll();
	}

}
