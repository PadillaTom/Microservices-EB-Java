package com.padillatomas.accounts.client;

import com.padillatomas.accounts.model.entity.Customer;
import com.padillatomas.accounts.model.entity.Loans;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("loans")
public interface LoansFeignClient {
    @RequestMapping(
            method = RequestMethod.POST,
            value = "myLoans",
            consumes = "application/json"
    )
    List<Loans> getLoansDetails(@RequestBody Customer customer);

}
