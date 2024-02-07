package org.dnyanyog.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.dnyanyog.dto.request.CustomerTranferAmountRequest;
import org.dnyanyog.dto.response.CustomerTransferAmountResponse;
import org.dnyanyog.entity.Account;
import org.dnyanyog.entity.Transactions;
import org.dnyanyog.repo.CustomerTransferAmountRepository;
import org.dnyanyog.repo.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerTransferAmountService {

	@Autowired
	CustomerTransferAmountRepository accountRepository;

	@Autowired
	TransactionsRepository transactionsRepository;

	public CustomerTransferAmountResponse transferAmount(CustomerTranferAmountRequest request) {

		CustomerTransferAmountResponse response = new CustomerTransferAmountResponse();

		Optional<Account> fromAccount = accountRepository.findByCardNo(request.getFromcardNo());
		Optional<Account> toAccount = accountRepository.findByCardNo(request.getTocardNo());


		if (fromAccount.isPresent() && toAccount.isPresent()) {

		    Account fromAccount1 = fromAccount.get();
		    Account toAccount1 = toAccount.get();
		    
		    // Check if the account status is "Open" (Corrected closing parenthesis here)
		    if (fromAccount1.getAccountStatus().equals("Open")) {
		    	
		        String senderAtmPin = fromAccount1.getAtmPin();

		        if (senderAtmPin.equals(request.getAtmPin())) {

		            int currentBalance = (int) fromAccount1.getBalance();

		            if (currentBalance >= request.getBalance()) {

		                int currentBalanceToAccount = (int) toAccount1.getBalance();

		                toAccount1.setBalance(currentBalanceToAccount + request.getBalance());
		                fromAccount1.setBalance(currentBalance - request.getBalance());

		                accountRepository.save(fromAccount1);
		                accountRepository.save(toAccount1);
		                
		                Transactions transactions = new Transactions();
		                Transactions transactions1 = new Transactions();

		                transactions.setCustomerId(fromAccount1.getCustomerId());
		                transactions.setBalance(request.getBalance());
		                transactions.setCardNo(fromAccount1.getCardNo());
		                transactions.setTransactionDate(LocalDateTime.now());
		                transactions.setTransactionType("Transfer Amount");
		                
		                transactions = transactionsRepository.save(transactions);

		                transactions1.setCustomerId(toAccount1.getCustomerId());
		                transactions1.setBalance(request.getBalance());
		                transactions1.setCardNo(toAccount1.getCardNo());
		                transactions1.setTransactionDate(LocalDateTime.now());
		                transactions1.setTransactionType("Received Amount");

		                transactions1 = transactionsRepository.save(transactions1);

		                response.setStatus("Success");
		                response.setMessage("Amount Transferred Successfully");

		            } else {
		                response.setStatus("Fail");
		                response.setMessage("Insufficient Balance");
		            }

		        } else {
		            response.setStatus("Fail");
		            response.setMessage("Incorrect ATM Pin");
		        }

		    } else {
		        response.setStatus("Fail");
		        response.setMessage("Your Account Has Been Locked. Please Contact The Admin");
		    }
		    
		} else {
		    response.setStatus("Fail");
		    response.setMessage("Account(s) not found");
		}

    
        return response;

	}

}