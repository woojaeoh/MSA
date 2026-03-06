package com.example.demo.product.application.command;

import com.example.demo.product.domain.Product;
import com.example.demo.product.dto.CreateProductRequest;
import com.example.demo.product.dto.UpdateProductRequest;

import java.util.UUID;

public interface ProductCommandUseCase {

    Product create(CreateProductRequest request, UUID actorId);

    Product update(UUID productId, UpdateProductRequest request, UUID actorId);

    void delete(UUID productId);
}
