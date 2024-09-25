package com.payments.demo;

import jakarta.persistence.Entity;

@Entity
public class Payment {
    private String id;
    private String currency;
    private Double amount;
    private Account counterparty;

    public Payment(String id, String currency, Double amount, Account counterparty) {
        this.id = id;
        this.currency = currency;
        this.amount = amount;
        this.counterparty = counterparty;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Account getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Account counterparty) {
        this.counterparty = counterparty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
