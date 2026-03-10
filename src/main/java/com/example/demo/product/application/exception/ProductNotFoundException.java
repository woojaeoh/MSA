package com.example.demo.product.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public class ProductNotFoundException extends ResponseStatusException {

    public ProductNotFoundException(UUID productId) {
        super(HttpStatus.NOT_FOUND, "Product not found: " + productId);
    }
}
