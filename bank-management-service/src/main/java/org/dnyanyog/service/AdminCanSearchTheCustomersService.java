package org.dnyanyog.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.dnyanyog.dto.response.AdminCanSearchTheCustomersResponse;
import org.dnyanyog.entity.Account;
import org.dnyanyog.entity.CustomerResgistration;
import org.dnyanyog.repo.AdminCanSearchTheCustomersRepository;
import org.dnyanyog.repo.GetTotalBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.Column;

@Service
public class AdminCanSearchTheCustomersService {

	@Autowired
	AdminCanSearchTheCustomersRepository repo;

	@Autowired
	private GetTotalBalanceRepository repo1;

	public AdminCanSearchTheCustomersResponse getSingleCustomer(Long customerId) {
		AdminCanSearchTheCustomersResponse response = new AdminCanSearchTheCustomersResponse();

		Optional<CustomerResgistration> receivedData = repo.findByCustomerId(customerId);
		Optional<Account> accountData = repo1.findByCustomerId(customerId);

		if (receivedData.isPresent() && accountData.isPresent()) {
			CustomerResgistration customer = receivedData.get();
			Account acc = accountData.get();


			response.setCustomerId(customer.getCustomerId());
			response.setFirstName(customer.getFirstName());
			response.setLastName(customer.getLastName());
			response.setDateOfBirth(customer.getDateOfBirth());
			response.setMobileNumber(customer.getMobileNo());
			response.setEmailId(customer.getEmailId());
			response.setGender(customer.getGender());
			response.setBranch(customer.getBranch());
			response.setPassword(customer.getPassword());
			response.setPermantAddress(customer.getPermanentAddress());
			response.setPresentAddress(customer.getPresentAddress());
			
			response.setAccountStatus(acc.getAccountStatus());


			response.setStatus("Success");
			response.setMessage("Customer Serach Successfully ");

		} else {
			response.setStatus("Fail");
			response.setMessage("Customer Not Found ");

		}
		return response;

	}

}