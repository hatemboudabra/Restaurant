package com.livrini.restaurant.controller;

import com.livrini.restaurant.dto.PaymentRequest;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Value("${stripe.secretKey}")
    private String secretKey;
    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }
    @PostMapping("/create-checkout-session")
    public Map<String, Object> createCheckoutSession(@RequestBody Map<String, Object> request) {
        Map<String, Object> responseData = new HashMap<>();
        try {
            int amount = (Integer) request.get("amount");

            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:4200/success")
                    .setCancelUrl("http://localhost:4200/cancel")
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency("usd")
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    .setName("Votre produit")  // Nom du produit
                                                                    .build())
                                                    .setUnitAmount((long) amount)     // Montant en cents
                                                    .build())
                                    .setQuantity(1L)                          // Quantité
                                    .build())
                    .build();

            Session session = Session.create(params);
            responseData.put("url", session.getUrl());  // URL vers la session de paiement Stripe Checkout
        } catch (Exception e) {
            e.printStackTrace();
            responseData.put("error", "Erreur lors de la création de la session de paiement");
        }
        return responseData;
    }

        @PostMapping("/create-payment-intent")
        public Map<String, Object> createPaymentIntent(@RequestBody Map<String, Object> request) {
            Map<String, Object> responseData = new HashMap<>();
            try {
                int amount = (Integer) request.get("amount");

                // Logique pour créer un PaymentIntent avec Stripe
                PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                        .setAmount((long) amount)
                        .setCurrency("usd")
                        .build();

                PaymentIntent intent = PaymentIntent.create(params);
                responseData.put("clientSecret", intent.getClientSecret());

            } catch (Exception e) {
                e.printStackTrace();
                responseData.put("error", "Erreur lors de la création du PaymentIntent");
            }
            return responseData;
        }
    }


