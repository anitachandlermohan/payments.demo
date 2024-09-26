package com.payments.demo;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class PaymentSpecifications {
    public static Specification<Payment> greaterThanMinimumAmount(Double minAmount) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("amount"), minAmount);
    }

    public static Specification<Payment> usesCurrencyInList(List<String> currencies){
        return (root, query, builder)  -> root.get("currency").in(currencies);
    }
}


