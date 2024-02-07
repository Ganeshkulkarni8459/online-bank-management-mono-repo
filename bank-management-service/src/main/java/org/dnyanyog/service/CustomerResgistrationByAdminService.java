package org.dnyanyog.service;

import java.time.LocalDate;

import org.dnyanyog.dto.request.CustomerResgisterByAdminRequest;
import org.dnyanyog.dto.response.CustomerResgisterByAdminResponse;
import org.dnyanyog.entity.CustomerResgistration;
import org.dnyanyog.entity.TotalAccounts;
import org.dnyanyog.repo.CustomerResgistrationRepository;
import org.dnyanyog.repo.GetTotalBankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CustomerResgistrationByAdminService {
	
	@Autowired 
	CustomerResgistrationRepository repo;
	
	@Autowired
	CustomerResgisterByAdminResponse response;
	
	@Autowired
	GetTotalBankAccountRepository repo1;
	
	@Transactional
	public CustomerResgisterByAdminResponse resgister(CustomerResgisterByAdminRequest adminResgisterRequest) {
		
		CustomerResgistration table = new CustomerResgistration();
		
		table.setFirstName(adminResgisterRequest.getFirstName());
		table.setLastName(adminResgisterRequest.getLastName());
		table.setDateOfBirth(adminResgisterRequest.getDateOfBirth());
		table.setMobileNo(adminResgisterRequest.getMobileNo());
		table.setEmailId(adminResgisterRequest.getEmailId());
		table.setGender(adminResgisterRequest.getGender());
		table.setBranch(adminResgisterRequest.getBranch());
		table.setPassword(adminResgisterRequest.getPasssword());
		table.setPermanentAddress(adminResgisterRequest.getPermanentAddress());
		table.setPresentAddress(adminResgisterRequest.getPresentAddress());
		table.setRegisterDate(LocalDate.now());
		
		table = repo.save(table);
		
		TotalAccounts totalAccount = new TotalAccounts();
		totalAccount.setFirstName(table.getFirstName());
		totalAccount.setMobileNumber(table.getMobileNo());
		totalAccount.setEmailId(table.getEmailId());
		
		totalAccount = repo1.save(totalAccount);
	
		response.setStatus("Success");
		response.setMessage("Customer Added Successfuly");
		response.getData().setFirstName(table.getFirstName());
		response.getData().setLastName(table.getLastName());
		response.getData().setEmailId(table.getEmailId());
		response.getData().setPasssword(table.getPassword());
		response.getData().setCustomerId(table.getCustomerId());
		
		return response;
		
	}
	
}
