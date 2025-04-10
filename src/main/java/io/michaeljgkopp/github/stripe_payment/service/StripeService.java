package io.michaeljgkopp.github.stripe_payment.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import io.michaeljgkopp.github.stripe_payment.dto.ProductRequest;
import io.michaeljgkopp.github.stripe_payment.dto.StripeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StripeService {

    // stripe API: productName, amount, quantity, currency -> returns sessionId and checkoutUrl

    @Value("${stripe.secretkey}")
    private String secretKey;

    public StripeResponse checkoutProducts(ProductRequest productRequest) {
        Stripe.apiKey = secretKey;

        // Create a new product with name
        SessionCreateParams.LineItem.PriceData.ProductData productData =
                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName(productRequest.getName()).build();

        // Create a new price for the product with unit price and currency
        SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency(productRequest.getCurrency() == null ? "EUR" : productRequest.getCurrency()) // USD
                .setUnitAmount(productRequest.getAmount())
                .setProductData(productData)
                .build();

        // Create a new line item with quantity and (unit) priceData (incl. productData/name)
        SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                .setQuantity(productRequest.getQuantity())
                .setPriceData(priceData)
                .build();

        // Create a new checkout session with the line item, payment mode, success and cancel URLs
        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8080/success")
                .setCancelUrl("http://localhost:8080/cancel")
                .addLineItem(lineItem)
                .build();

        Session session;

        try {
            session = Session.create(params);
        } catch (StripeException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Failed to create Stripe session", e);
        }

        return StripeResponse.builder()
                .status("SUCCESS")
                .message("Payment session created")
                .sessionId(session.getId())
                .sessionUrl(session.getUrl())
                .build();
    }
}
