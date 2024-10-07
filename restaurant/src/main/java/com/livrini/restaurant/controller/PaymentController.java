package com.livrini.restaurant.controller;

import com.livrini.restaurant.service.PaymentService;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.stripe.exception.*;
@RestController
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @PostMapping("/charge")
    public String chargePayment(@RequestParam Double amount,
                                @RequestParam String currency,
                                @RequestParam String description,
                                @RequestParam String stripeToken) {
        try {
            Charge charge = paymentService.charge(amount, currency, description, stripeToken);
            return "Payment successful! Charge ID: " + charge.getId();
        } catch (AuthenticationException e) {
            return "Authentication error: " + e.getMessage();
        } catch (InvalidRequestException e) {
            return "Invalid request error: " + e.getMessage();
        } catch (APIConnectionException e) {
            return "Connection error: " + e.getMessage();
        } catch (CardException e) {
            return "Card error: " + e.getMessage();
        } catch (APIException e) {
            return "API error: " + e.getMessage();
        } catch (Exception e) {
            return "Unexpected error: " + e.getMessage();
        }
    }
}
