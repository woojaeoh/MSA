package com.example.demo.product.domain.repository.query;

import com.example.demo.product.domain.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductQueryRepository {

    Optional<Product> findById(UUID productId);

    List<Product> findAll();
}
