package com.livrini.restaurant.service;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

       @Value("${stripe.api.secret.key}")
    private String apiKey;

    public PaymentService() {
        Stripe.apiKey = apiKey;
    }
    public Charge charge(Double amount, String currency, String description, String stripeToken)
            throws InvalidRequestException, APIConnectionException, CardException, APIException, AuthenticationException {

        amount = amount * 100;

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", amount.intValue());
        chargeParams.put("currency", currency);
        chargeParams.put("description", description);
        chargeParams.put("source", stripeToken);

        return Charge.create(chargeParams);
    }
}
