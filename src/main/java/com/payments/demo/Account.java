package com.payments.demo;

import jakarta.persistence.Embeddable;

@Embeddable
public class Account {
    private String accountNumber;
    private String sortCode;
    private String type;

    public Account(String accountNumber, String sortCode, String type) {
        this.accountNumber = accountNumber;
        this.sortCode = sortCode;
        this.type = type;
    }

    public Account(){}

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }
}
