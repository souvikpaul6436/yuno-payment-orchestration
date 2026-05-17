package com.yuno.payment_orchestration.provider;

import com.yuno.payment_orchestration.dto.PaymentRequest;
import com.yuno.payment_orchestration.dto.ProviderResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProviderAConnector implements PaymentProvider {

    @Override
    public ProviderResponse processPayment(PaymentRequest request) {

        return ProviderResponse.builder()
                .success(true)
                .provider("Provider-A")
                .transactionId(UUID.randomUUID().toString())
                .build();
    }

    @Override
    public String getProviderName() {
        return "Provider-A";
    }
}