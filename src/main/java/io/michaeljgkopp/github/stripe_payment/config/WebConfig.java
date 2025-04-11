package io.michaeljgkopp.github.stripe_payment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// ONLY NEEDED IF FRONTEND IS ON A DIFFERENT PORT
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**")
//                .allowedOrigins("http://localhost:8080") // Replace with your frontend URL
//                .allowedMethods("POST");
//    }
//}
