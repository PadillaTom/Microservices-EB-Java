package com.padillatomas.accounts.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Cards {

    private Long cardId;

    private Long customerId;

    private String cardNumber;

    private String cardType;

    private int totalLimit;

    private int amountUsed;

    private int availableAmount;

    private Date createDt;
}
