package io.michaeljgkopp.github.stripe_payment.controller;

import io.michaeljgkopp.github.stripe_payment.dto.ProductRequest;
import io.michaeljgkopp.github.stripe_payment.dto.StripeResponse;
import io.michaeljgkopp.github.stripe_payment.service.StripeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/v1")
@RequiredArgsConstructor
@Slf4j
public class ProductCheckoutController {

    private final StripeService stripeService;

    @PostMapping("/checkout")
    public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody ProductRequest productRequest) {
        log.info("Checkout products request: {}", productRequest);
        StripeResponse stripeResponse = stripeService.checkoutProducts(productRequest);
        log.info("Stripe response: {}", stripeResponse);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stripeResponse);
    }
}
