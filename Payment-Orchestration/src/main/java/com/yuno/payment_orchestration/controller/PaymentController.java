package com.yuno.payment_orchestration.controller;

import com.yuno.payment_orchestration.dto.PaymentRequest;
import com.yuno.payment_orchestration.dto.PaymentResponse;
import com.yuno.payment_orchestration.entity.Payment;
import com.yuno.payment_orchestration.service.IdempotencyService;
import com.yuno.payment_orchestration.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final IdempotencyService idempotencyService;

    @PostMapping
    public PaymentResponse createPayment(
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @Valid @RequestBody PaymentRequest request) {

        if (idempotencyService.isDuplicate(idempotencyKey)) {

            return (PaymentResponse)
                    idempotencyService.get(idempotencyKey);
        }

        PaymentResponse response =
                paymentService.createPayment(request);

        idempotencyService.save(idempotencyKey, response);

        return response;
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable UUID id) {

        return paymentService.getPayment(id);
    }
}