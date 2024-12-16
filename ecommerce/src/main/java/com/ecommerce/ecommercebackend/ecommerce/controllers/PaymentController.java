package com.ecommerce.ecommercebackend.ecommerce.controllers;

import com.ecommerce.ecommercebackend.ecommerce.dto.PaymentRequest;
import com.ecommerce.ecommercebackend.ecommerce.dto.PaymentResponse;
import com.ecommerce.ecommercebackend.ecommerce.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = paymentService.createPayment(paymentRequest);
        if ("success".equalsIgnoreCase(paymentResponse.getStatus())) {
            return ResponseEntity.ok(paymentResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(paymentResponse);
        }
    }
}