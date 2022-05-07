package com.padillatomas.accounts.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Loans {

    private Long loanNumber;

    private Long customerId;

    private Date startDt;

    private String loanType;

    private int totalLoan;

    private int amountPaid;

    private int outstandingAmount;

    private String createDt;
}
