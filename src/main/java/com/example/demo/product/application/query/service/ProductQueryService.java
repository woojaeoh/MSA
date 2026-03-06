package com.example.demo.product.application.query.service;

import com.example.demo.product.application.query.ProductQueryUseCase;
import com.example.demo.product.domain.Product;
import com.example.demo.product.domain.repository.query.ProductQueryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ProductQueryService implements ProductQueryUseCase {

    private final ProductQueryRepository productQueryRepository;

    public ProductQueryService(ProductQueryRepository productQueryRepository) {
        this.productQueryRepository = productQueryRepository;
    }

    @Override
    public Product getById(UUID productId) {
        return productQueryRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @Override
    public List<Product> getAll() {
        return productQueryRepository.findAll();
    }
}
