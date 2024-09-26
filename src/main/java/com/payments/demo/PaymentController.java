package com.payments.demo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static com.payments.demo.PaymentSpecifications.greaterThanMinimumAmount;
import static com.payments.demo.PaymentSpecifications.usesCurrencyInList;

@Validated
@RestController
public class PaymentController {

    @Autowired
    private PaymentValidator paymentValidator;

    @InitBinder(value = "payment")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(paymentValidator);
    }

    private final PaymentRepository paymentRepository;

    PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/payments/{id}")
    public Payment getPayment(@PathVariable Long id) {
        return paymentRepository.findById(id).orElseThrow();
    }

    @GetMapping("/payments")
    public List<Payment> getPayments(
            @RequestParam(value = "minAmount") Double minAmount,
            @RequestParam(value = "currencies") List<String> currencies
    ) {
        return paymentRepository.findAll(Specification.where(
                greaterThanMinimumAmount(minAmount).and(usesCurrencyInList(currencies))
        ));
    }

    @PostMapping("/payments")
    public ResponseEntity<Payment> createPayment(@Valid @RequestBody Payment payment) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(paymentRepository.save(payment));
    }
}
