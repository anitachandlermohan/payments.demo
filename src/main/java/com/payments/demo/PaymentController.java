package com.payments.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {
    @GetMapping("/payments/{id}")
    public Payment getPayment(@PathVariable String id){
        return new Payment(id, "GBP", 0.0, new Account(
                "1234",
                "3333",
                "accountType"
        ));
    }

    @GetMapping("/payments")
    public List<Payment> getPayments(
            @RequestParam(value = "minAmount")Double minAmount,
            @RequestParam(value = "currencies")List<String> currencies
            ){

        return List.of();
    }

    @PostMapping("/payments")
    public Payment createPayment(@RequestBody Payment payment){

        return payment;
    }
}
