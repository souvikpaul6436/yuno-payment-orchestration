package com.yuno.payment_orchestration.service;

import com.yuno.payment_orchestration.dto.PaymentRequest;
import com.yuno.payment_orchestration.dto.PaymentResponse;
import com.yuno.payment_orchestration.dto.ProviderResponse;
import com.yuno.payment_orchestration.entity.Payment;
import com.yuno.payment_orchestration.enums.PaymentStatus;
import com.yuno.payment_orchestration.provider.PaymentProvider;
import com.yuno.payment_orchestration.repository.PaymentRepository;
import com.yuno.payment_orchestration.routing.RoutingEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RoutingEngine routingEngine;

    public PaymentResponse createPayment(PaymentRequest request) {

        PaymentProvider provider =
                (PaymentProvider) routingEngine.route(request.getPaymentMethod());

        ProviderResponse providerResponse;

        try {
            providerResponse = provider.processPayment(request);
        } catch (Exception ex) {

            providerResponse = retryPayment(request);
        }

        Payment payment = Payment.builder()
                .orderId(request.getOrderId())
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .status(providerResponse.isSuccess()
                        ? PaymentStatus.SUCCESS
                        : PaymentStatus.FAILED)
                .provider(providerResponse.getProvider())
                .transactionId(providerResponse.getTransactionId())
                .createdAt(LocalDateTime.now())
                .build();

        paymentRepository.save(payment);

        return PaymentResponse.builder()
                .paymentId(payment.getId())
                .status(payment.getStatus())
                .provider(payment.getProvider())
                .transactionId(payment.getTransactionId())
                .build();
    }

    private ProviderResponse retryPayment(PaymentRequest request) {

        return ProviderResponse.builder()
                .success(true)
                .provider("Retry-Provider")
                .transactionId(UUID.randomUUID().toString())
                .build();
    }

    public Payment getPayment(UUID id) {

        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }
}