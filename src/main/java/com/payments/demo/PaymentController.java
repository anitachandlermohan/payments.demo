package com.payments.demo;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.payments.demo.PaymentSpecifications.greaterThanMinimumAmount;
import static com.payments.demo.PaymentSpecifications.usesCurrencyInList;

@RestController
public class PaymentController {

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
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }
}
