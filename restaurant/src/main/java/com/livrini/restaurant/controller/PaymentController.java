package com.livrini.restaurant.controller;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    @PostMapping("/create-payment-intent")
    public Map<String, Object> createPaymentIntent(@RequestBody Map<String, Object> request) {
        Map<String, Object> responseData = new HashMap<>();
        try {
            int amount = (Integer) request.get("amount");

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount((long) amount)
                    .setCurrency("usd")
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);
            responseData.put("clientSecret", intent.getClientSecret());

        } catch (Exception e) {
            e.printStackTrace();
            responseData.put("error", "Failed to create payment intent");
        }
        return responseData;
    }
}

