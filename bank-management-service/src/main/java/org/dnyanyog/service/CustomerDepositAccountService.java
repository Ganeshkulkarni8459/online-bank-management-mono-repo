package org.dnyanyog.service;

import java.time.LocalDateTime;
import java.util.List;

import org.dnyanyog.dto.request.CustomerDepositAmountRequest;
import org.dnyanyog.dto.response.CustomerDepositAmountResponse;
import org.dnyanyog.entity.Account;
import org.dnyanyog.entity.Transactions;
import org.dnyanyog.repo.CustomerDepositAmountRepository;
import org.dnyanyog.repo.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDepositAccountService {
	
	@Autowired
	CustomerDepositAmountRepository customerDepositAmountRepository;
	
	@Autowired
	TransactionsRepository transactionsRepository;

	public CustomerDepositAmountResponse depositAmount(CustomerDepositAmountRequest request) {
		
		CustomerDepositAmountResponse response=new CustomerDepositAmountResponse();
		
		 List<Account> account = customerDepositAmountRepository.findByCardNoAndAtmPin(request.getCardNo(), request.getAtmPin());
		 
		 if(account.isEmpty()) {
			 response.setStatus("Fail");
			 response.setResponse("Card No And Atm Pin Does Not Match");
		 }else {
			 Account acc=account.get(0);
			 
			 if(acc.getAccountStatus().equals("Open")) {
				 int currentBalance = (int) acc.getBalance();
				 
				 acc.setBalance(currentBalance + request.getBalance());
				 
				 acc=customerDepositAmountRepository.save(acc);
				 //System.out.println(acc);
				 
				 Transactions transactions = new Transactions();

					transactions.setCustomerId(acc.getCustomerId());
					transactions.setBalance(request.getBalance());
					transactions.setCardNo(acc.getCardNo());
					transactions.setTransactionDate(LocalDateTime.now());
					transactions.setTransactionType("Deposit Amount");

					transactions=transactionsRepository.save(transactions);
			
				 
				 response.setStatus("Success");
				 response.setResponse("Amount Add Sucessfully");
			 }else {
				 response.setStatus("Fail");
				 response.setResponse("Your Account Has Temporary Lock, Please Contact The Admin");
			 }
			 
			 
		 }
		return response;
	}



}