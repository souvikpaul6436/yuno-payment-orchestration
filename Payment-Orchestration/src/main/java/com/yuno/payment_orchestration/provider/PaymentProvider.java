package com.yuno.payment_orchestration.provider;

import com.yuno.payment_orchestration.dto.PaymentRequest;
import com.yuno.payment_orchestration.dto.ProviderResponse;

public interface PaymentProvider {

    ProviderResponse processPayment(PaymentRequest request);

    String getProviderName();
}