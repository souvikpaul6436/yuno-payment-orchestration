package com.yuno.payment_orchestration.dto;

import com.yuno.payment_orchestration.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PaymentResponse {

    private UUID paymentId;

    private PaymentStatus status;

    private String provider;

    private String transactionId;
}