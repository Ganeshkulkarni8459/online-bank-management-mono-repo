package org.dnyanyog.service;

import java.time.LocalDateTime;
import java.util.List;

import org.dnyanyog.dto.request.CustomerWithdrawAmountRequest;
import org.dnyanyog.dto.response.CustomerWithdrawAmountResponse;
import org.dnyanyog.entity.Account;
import org.dnyanyog.entity.Transactions;
import org.dnyanyog.repo.CustomerWithdrawAmountRepository;
import org.dnyanyog.repo.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerWithdrawAmountService {

    @Autowired
    CustomerWithdrawAmountRepository customerDepositWithdrawRepository;

    @Autowired
    TransactionsRepository transactionsRepository;

    public CustomerWithdrawAmountResponse withdrawAmount(CustomerWithdrawAmountRequest request) {

        CustomerWithdrawAmountResponse response = new CustomerWithdrawAmountResponse();

        List<Account> accounts = customerDepositWithdrawRepository.findByCardNoAndAtmPin(request.getCardNo(), request.getAtmPin());

        if (accounts.isEmpty()) {
            response.setStatus("Fail");
            response.setResponse("Pin Incorrect");
        } else {
            Account acc = accounts.get(0);

            if (acc.getAccountStatus().equals("Open")) {
                double currentBalance = acc.getBalance();  

                if (currentBalance > request.getBalance()) {
                    acc.setBalance(currentBalance - request.getBalance());

                    acc = customerDepositWithdrawRepository.save(acc);

                    Transactions transactions = new Transactions();

                    transactions.setCustomerId(acc.getCustomerId());
                    transactions.setBalance(request.getBalance());
                    transactions.setCardNo(acc.getCardNo());
                    transactions.setTransactionDate(LocalDateTime.now());
                    transactions.setTransactionType("Withdraw Amount");

                    transactions = transactionsRepository.save(transactions);

                    response.setStatus("Success");
                    response.setResponse("Amount Withdrawn Successfully");
                    return response;
                }
                response.setStatus("Fail");
                response.setResponse("Insufficient Balance");
            } else {
                response.setStatus("Fail");
                response.setResponse("Your Account Has Lock. Please Contact The Admin");
            }
        }

        return response;
    }
}
