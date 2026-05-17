package com.yuno.payment_orchestration.routing;

import com.yuno.payment_orchestration.enums.PaymentMethod;
import com.yuno.payment_orchestration.provider.ProviderAConnector;
import com.yuno.payment_orchestration.provider.ProviderBConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoutingEngine {

    private final ProviderAConnector providerAConnector;
    private final ProviderBConnector providerBConnector;

    public Object route(PaymentMethod paymentMethod) {

        return switch (paymentMethod) {
            case CARD -> providerAConnector;
            case UPI -> providerBConnector;
        };
    }
}