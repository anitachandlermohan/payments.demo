package com.payments.demo;

import jakarta.persistence.*;

@Entity
public class Payment {

    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String currency;
    private Double amount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "accountNumber", column = @Column(name = "counterparty_account_umber")),
            @AttributeOverride( name = "sortCode", column = @Column(name = "counterparty_sort_code")),
            @AttributeOverride( name = "type", column = @Column(name = "counterparty_type"))
    })
    private Account counterparty;

    public Payment(String currency, Double amount, Account counterparty) {
        this.currency = currency;
        this.amount = amount;
        this.counterparty = counterparty;
    }

    public Payment(){}

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

    public Long getId() {
        return id;
    }

}
