package org.dnyanyog.service;


import org.dnyanyog.dto.response.AdminCanDeleteTheCustomersResponse;
import org.dnyanyog.entity.Account;
import org.dnyanyog.entity.CustomerResgistration;

import java.util.List;



import org.dnyanyog.repo.AdminCanDeleteTheCustomersRepository;
import org.dnyanyog.repo.AdminCanDeleteTheCustomersRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AdminCanDeleteTheCustomersService {

	@Autowired
	AdminCanDeleteTheCustomersRepository repo;

	@Autowired
	AdminCanDeleteTheCustomersRepository1 repo1;
	
	
    @Transactional
	public AdminCanDeleteTheCustomersResponse deleteCustomer(Long customerId) {

		AdminCanDeleteTheCustomersResponse response = new AdminCanDeleteTheCustomersResponse();

		List<CustomerResgistration> liCustomer = repo.findByCustomerId(customerId);

		List<Account> liCustomer1 = repo1.findByCustomerId(customerId);

		if (liCustomer1.isEmpty() && liCustomer.isEmpty()) {
			response.setStatus("Fail");
			response.setMessage("User Not Found");
		} else {
			
			repo.deleteById(customerId);

			repo1.deleteById(customerId);

			response.setStatus("Success");
			response.setMessage("User Delete Successfully");
		}

		return response;

	}

}
