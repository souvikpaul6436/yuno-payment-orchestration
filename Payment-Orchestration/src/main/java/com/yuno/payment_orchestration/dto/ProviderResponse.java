package com.yuno.payment_orchestration.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProviderResponse {

    private boolean success;

    private String transactionId;

    private String provider;
}