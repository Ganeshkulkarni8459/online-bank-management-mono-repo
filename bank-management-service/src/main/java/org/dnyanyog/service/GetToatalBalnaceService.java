package org.dnyanyog.service;

import java.util.Optional;

import org.dnyanyog.dto.response.GetTotalBalanceResponse;
import org.dnyanyog.entity.Account;
import org.dnyanyog.repo.GetTotalBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetToatalBalnaceService {
	
	@Autowired
	GetTotalBalanceRepository repo;

	public GetTotalBalanceResponse getTotalBalance(Long customerId) {
		
		GetTotalBalanceResponse response=new GetTotalBalanceResponse();
		
		Optional<Account> receivedData=repo.findByCustomerId(customerId);
		
		if(receivedData.isPresent()) {
			Account acc=receivedData.get();
			
			response.setBalance((int) acc.getBalance());
			
			response.setStatus("Success");
			response.setMessage("Get Balance Sucessfully");
		}else {

			response.setStatus("Fail");
			response.setMessage("CustomerId Not Found");
		}
		
		return response;
	}


}
