package org.dnyanyog.service;

import java.util.List;
import java.util.Optional;

import org.dnyanyog.dto.request.AddAccountRequest;
import org.dnyanyog.dto.response.AddAccountResponse;
import org.dnyanyog.entity.Account;
import org.dnyanyog.entity.CustomerResgistration;
import org.dnyanyog.entity.TotalAccounts;
import org.dnyanyog.repo.AddAccountServiceRepository;
import org.dnyanyog.repo.CustomerResgistrationRepository;
import org.dnyanyog.repo.GetTotalBankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class AddAccountService extends Account {

	@Autowired
	AddAccountServiceRepository repo;

	@Autowired
	AddAccountResponse addAccountResponse;
	
	@Autowired
	CustomerResgistrationRepository repo1;
	
	@Autowired
	GetTotalBankAccountRepository repo2;
//	public static String generateAtmPin(){
//		int randomNo=(int) (Math.random()*9000)+1000;
//		String pin=String.valueOf(randomNo);
//		return pin;
//		
//	}
	@Transactional
	public AddAccountResponse addAccount(Long customerId, AddAccountRequest addAccountRequest) {

		AddAccountResponse addAccountResponse = new AddAccountResponse();

		Optional<CustomerResgistration> receivedData = repo1.findByCustomerId(customerId);

		List<Account> receivedData1 = repo.findByCustomerId(customerId);

		if (receivedData.isPresent()) {

			if (receivedData1.size() == 0) {
				Account account = new Account();

				account.setCustomerId(customerId);
				account.setCardNo(addAccountRequest.getCardNo());
				account.setAccountType(addAccountRequest.getAccountType());
				account.setBalance(addAccountRequest.getBalance());
				account.setAccountStatus(addAccountRequest.getAccountStatus());
				account.setAtmPin(addAccountRequest.getAtmPin());

				account = repo.save(account);
				
				TotalAccounts totalAccount = new TotalAccounts();
				totalAccount.setAtmPin(account.getAtmPin());
				totalAccount.setCustomerId(account.getCustomerId());
				totalAccount.setCardNo(account.getCardNo());
				totalAccount.setAccountType(account.getAccountType());
				totalAccount.setBalance(account.getBalance());
				totalAccount.setAccountStatus(account.getAccountStatus());
				
				totalAccount = repo2.save(totalAccount);
				
				addAccountResponse.setStatus("Success");
				addAccountResponse.setMessage("Customer Account Add Successfully");

//				addAccountResponse.setCustomerId(account.getCustomerId());
//				addAccountResponse.setCardNo(account.getCardNo());
//				addAccountResponse.setAccountType(account.getAccountType());
//				addAccountResponse.setBalance(account.getBalance());
//				addAccountResponse.setAtmPin(account.getAtmPin());
//				addAccountResponse.setAccountStatus(account.getAccountStatus());
			} else {
				addAccountResponse.setStatus("Fail");
				addAccountResponse.setMessage("Above CustomerId Id Account is Already Generated");
			}

		} else {
			addAccountResponse.setStatus("Fail");
			addAccountResponse.setMessage("Customer Table Id Does Not Match");
		}

		return addAccountResponse;

	}
}
