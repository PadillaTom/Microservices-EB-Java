package com.padillatomas.accounts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.padillatomas.accounts.client.CardsFeignClient;
import com.padillatomas.accounts.client.LoansFeignClient;
import com.padillatomas.accounts.config.AccountsServiceConfig;
import com.padillatomas.accounts.model.Properties;
import com.padillatomas.accounts.model.entity.*;
import com.padillatomas.accounts.repository.AccountsRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class AccountsController {

    private final AccountsRepository accountsRepository;

    @Autowired
    AccountsServiceConfig accountsServiceConfig;

    @Autowired
    LoansFeignClient loansFeignClient;

    @Autowired
    CardsFeignClient cardsFeignClient;


//    === GET Properties ===
    @GetMapping("/accounts/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        System.out.println(accountsServiceConfig);
        Properties properties = new Properties(
                accountsServiceConfig.getMsg(),
                accountsServiceConfig.getBuildVersion(),
                accountsServiceConfig.getMailDetails(),
                accountsServiceConfig.getActiveBranches()
        );

        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }

//    === My Account ===
    @PostMapping("/myAccount")
    public Accounts getAccountDetails(@RequestBody Customer customerRequest){
        Accounts accounts = accountsRepository.findByCustomerId(customerRequest.getCustomerId());
        if(accounts != null){
            return accounts;
        } else {
            return null;
        }
    }

//    === Customer Details ===
    @PostMapping("myCustomerDetails")
    public CustomerDetails myCustomerDetails(@RequestBody Customer customer){
        Accounts myAccounts = accountsRepository.findByCustomerId(customer.getCustomerId());
        List<Loans> myLoans = loansFeignClient.getLoansDetails(customer);
        List<Cards> myCards = cardsFeignClient.getCardDetails(customer);

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(myAccounts);
        customerDetails.setLoans(myLoans);
        customerDetails.setCards(myCards);

        return customerDetails;
    }



}
