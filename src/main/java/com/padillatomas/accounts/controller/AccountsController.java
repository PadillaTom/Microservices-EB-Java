package com.padillatomas.accounts.controller;

import com.padillatomas.accounts.model.entity.Accounts;
import com.padillatomas.accounts.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountsController {

    private final AccountsRepository accountsRepository;

    @PostMapping("/myAccount")
    public Accounts getAccountDetails(@RequestBody Customer customerRequest){
        Accounts accounts = accountsRepository.findByCustomerId(customerRequest.getCustomerId());
        if(accounts != null){
            return accounts;
        } else {
            return null;
        }
    }

}
