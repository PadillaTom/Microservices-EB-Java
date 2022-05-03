package com.padillatomas.loans.controller;

import com.padillatomas.loans.model.entity.Customer;
import com.padillatomas.loans.model.entity.Loans;
import com.padillatomas.loans.repository.LoansRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoansRepository loansRepository;

    @PostMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestBody Customer customer) {
        List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
         if (loans != null) {
            return loans;
        } else {
            return null;
        }
    }

}
