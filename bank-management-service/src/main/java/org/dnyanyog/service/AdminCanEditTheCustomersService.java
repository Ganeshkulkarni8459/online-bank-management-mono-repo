package org.dnyanyog.service;

import java.util.Optional;

import org.dnyanyog.dto.request.AdminCanEditCustomerRequest;
import org.dnyanyog.dto.request.AdminCanEditTheCustomersRequest;
import org.dnyanyog.dto.response.AdminCanEditTheCustomersResponse;
import org.dnyanyog.entity.Account;
import org.dnyanyog.entity.CustomerResgistration;
import org.dnyanyog.repo.AdminCanEditTheCustomersRepository;
import org.dnyanyog.repo.GetTotalBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminCanEditTheCustomersService {

	@Autowired
	AdminCanEditTheCustomersRepository repo;
	
	@Autowired
	GetTotalBalanceRepository repo1;
	
	 

	public AdminCanEditTheCustomersResponse updateCustomer(Long customerId, AdminCanEditCustomerRequest request) {

		AdminCanEditTheCustomersResponse response = new AdminCanEditTheCustomersResponse();

		Optional<CustomerResgistration> receivedData = repo.findByCustomerId(customerId);
		
		Optional<Account> accountData = repo1.findByCustomerId(customerId);


		if (receivedData.isPresent() && accountData.isPresent()) {
			CustomerResgistration customer = receivedData.get();
			Account acc=accountData.get();

			customer.setFirstName(request.getFirstName());
			customer.setLastName(request.getLastName());
		//	customer.setDateOfBirth(request.getDateOfBirth());
			customer.setMobileNo(request.getMobileNo());
			customer.setEmailId(request.getEmailId());
		//	customer.setGender(request.getGender());
		//	customer.setBranch(request.getBranch());
			customer.setPassword(request.getPassword());
		//	customer.setPermanentAddress(request.getPermanentAddress());
			customer.setPresentAddress(request.getPresentAddress());
			
			acc.setAccountStatus(request.getAccountStatus());

			customer = repo.save(customer);

			response.setStatus("Success");
			response.setMessage("Customer Updated Successully");

		}else {
			response.setStatus("Fail");
			response.setMessage("Customer Not Found");
		}
		return response;

		
	}

}