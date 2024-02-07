package org.dnyanyog.service;

import java.util.List;

import org.dnyanyog.dto.request.CustomerCanGetTransactionsRequest;
import org.dnyanyog.entity.Transactions;
import org.dnyanyog.repo.CustomerCanGetTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transaction;

@Service
public class CustomerCanGetTransactionsService {

	@Autowired
	CustomerCanGetTransactionsRepository repo;

//	@Autowired
//	public CustomerCanGetTransactionsService(CustomerCanGetTransactionsRepository transactionRepository) {
//		this.repo = transactionRepository;
//	}

	public List<Transactions> getTransactionsByCustomerId(Long customerId) {
		return repo.findByCustomerId(customerId);
	}
}
