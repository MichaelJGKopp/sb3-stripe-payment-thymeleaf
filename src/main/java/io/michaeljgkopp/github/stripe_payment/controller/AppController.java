package io.michaeljgkopp.github.stripe_payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping
    public String showHome() {
        return "index";
    }

    @GetMapping("/success")
    public String showSuccess() {
        return "success";
    }
}
