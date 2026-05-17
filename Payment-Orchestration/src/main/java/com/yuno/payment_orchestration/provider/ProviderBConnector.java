package com.yuno.payment_orchestration.provider;

import com.yuno.payment_orchestration.dto.PaymentRequest;
import com.yuno.payment_orchestration.dto.ProviderResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProviderBConnector implements PaymentProvider {

    @Override
    public ProviderResponse processPayment(PaymentRequest request) {

        return ProviderResponse.builder()
                .success(true)
                .provider("Provider-B")
                .transactionId(UUID.randomUUID().toString())
                .build();
    }

    @Override
    public String getProviderName() {
        return "Provider-B";
    }
}