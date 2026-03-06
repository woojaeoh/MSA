package com.example.demo.product.application.port.in;

import com.example.demo.product.domain.Product;
import com.example.demo.product.dto.CreateProductRequest;
import com.example.demo.product.dto.UpdateProductRequest;

import java.util.List;
import java.util.UUID;

public interface ProductUseCase {

    Product create(CreateProductRequest request, UUID actorId);

    Product getById(UUID productId);

    List<Product> getAll();

    Product update(UUID productId, UpdateProductRequest request, UUID actorId);

    void delete(UUID productId);
}
