package org.dnyanyog.service;

import java.util.List;

import org.dnyanyog.dto.request.CustomerLoginRequest;
import org.dnyanyog.dto.response.CustomerLoginResponse;
import org.dnyanyog.entity.CustomerResgistration;
import org.dnyanyog.repo.CustomerLoginRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerLoginService {
	
	@Autowired
	CustomerLoginRespository repo;
	
	public CustomerLoginResponse login(CustomerLoginRequest customerLoginRequest) {

		CustomerLoginResponse response = new CustomerLoginResponse();
		List<CustomerResgistration> liCustomer = repo.findByEmailIdAndPassword(customerLoginRequest.getEmailId(),customerLoginRequest.getPassword());

		if (liCustomer.size() == 1) {
			response.setStatus("Success");
			response.setMessage("User Found!");
		} else {
			response.setStatus("Fail");
			response.setMessage("User Not Found");
		}

		return response;

	}

}
