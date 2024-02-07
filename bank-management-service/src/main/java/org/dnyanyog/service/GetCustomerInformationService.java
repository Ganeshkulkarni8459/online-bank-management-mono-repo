package org.dnyanyog.service;

import java.util.Optional;

import org.dnyanyog.dto.response.GetCustomerInformationResponse;
import org.dnyanyog.entity.Account;
import org.dnyanyog.entity.CustomerResgistration;
import org.dnyanyog.repo.GetCustomerInformationRepository;
import org.dnyanyog.repo.GetTotalBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetCustomerInformationService {

    @Autowired
    private GetCustomerInformationRepository repo;

    @Autowired
    private GetTotalBalanceRepository repo1;

    public GetCustomerInformationResponse getCustomerInformation(Long customerId) {
        GetCustomerInformationResponse response = new GetCustomerInformationResponse();

        Optional<CustomerResgistration> customerData = repo.findByCustomerId(customerId);
        Optional<Account> accountData = repo1.findByCustomerId(customerId);

        if (customerData.isPresent() && accountData.isPresent()) {
            CustomerResgistration customer = customerData.get();
            Account account = accountData.get();

            response.setStatus("Success");
            response.setMessage("Customer found successfully");

            response.setFirstName(customer.getFirstName());
            response.setLastname(customer.getLastName());
            response.setMobileNo(customer.getMobileNo());
            response.setEmailId(customer.getEmailId());
            response.setBranch(customer.getBranch());
            response.setDateOfBirth(customer.getDateOfBirth());
            response.setCardNo(account.getCardNo());
            response.setAtmPin(account.getAtmPin());
            response.setAccountType(account.getAccountType());
            response.setRegisterDate(customer.getRegisterDate());
        } else {
            response.setStatus("Fail");
            response.setMessage("Customer not found");
        }

        return response;
    }
}

