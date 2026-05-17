package com.yuno.payment_orchestration.dto;

import com.yuno.payment_orchestration.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {

    @NotNull
    private String orderId;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private PaymentMethod paymentMethod;
}