package com.padillatomas.loans.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.padillatomas.loans.config.LoansServiceConfig;
import com.padillatomas.loans.model.Properties;
import com.padillatomas.loans.model.entity.Customer;
import com.padillatomas.loans.model.entity.Loans;
import com.padillatomas.loans.repository.LoansRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoansRepository loansRepository;

    @Autowired
    LoansServiceConfig loansServiceConfig;

    //    === GET Properties ===
    @GetMapping("/loans/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        System.out.println(loansServiceConfig);
        Properties properties = new Properties(
                loansServiceConfig.getMsg(),
                loansServiceConfig.getBuildVersion(),
                loansServiceConfig.getMailDetails(),
                loansServiceConfig.getActiveBranches()
        );

        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }

//    === My Loans ===
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
