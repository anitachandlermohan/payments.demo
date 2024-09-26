package com.payments.demo;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

public class PaymentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Payment.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Payment payment = (Payment) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "amount.required");
        if(payment.getAmount().compareTo(BigDecimal.ZERO) < 0){
            errors.rejectValue("amount", "amount.greaterThan0", "amount must be greater than 0.0");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currency", "currency.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "counterparty", "counterparty.required");


        if(payment.getCounterparty().getAccountNumber().isEmpty()){
            errors.rejectValue("counterparty.accountNumber", "counterparty.accountNumber.required");
        }
        if(payment.getCounterparty().getAccountNumber().length() != 8){
            errors.rejectValue("counterparty.accountNumber", "counterparty.accountNumber.length_must_be_8");
        }
        if(!payment.getCounterparty().getAccountNumber().matches("[0-9]+")){
            errors.rejectValue("counterparty.accountNumber", "counterparty.accountNumber.should_only_contain_digits");
        }


        if(payment.getCounterparty().getSortCode().isEmpty()){
            errors.rejectValue("counterparty.sortCode", "counterparty.sortCode.required");
        }
        if(payment.getCounterparty().getSortCode().length() != 6){
            errors.rejectValue("counterparty.sortCode", "counterparty.sortCode.length_must_be_6");
        }
        if(!payment.getCounterparty().getSortCode().matches("[0-9]+")){
            errors.rejectValue("counterparty.sortCode", "counterparty.sortCode.should_only_contain_digits");
        }

        if(payment.getCounterparty().getType().isEmpty()){
            errors.rejectValue("counterparty.type", "counterparty.type.required");
        }

    }
}
