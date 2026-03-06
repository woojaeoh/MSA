package com.example.demo.product.application.query;

import com.example.demo.product.domain.Product;

import java.util.List;
import java.util.UUID;

public interface ProductQueryUseCase {

    Product getById(UUID productId);

    List<Product> getAll();
}
