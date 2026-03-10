package com.example.demo.product.domain.repository.query;

import com.example.demo.product.adapter.out.persistence.ProductJpaRepository;
import com.example.demo.product.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductQueryRepositoryAdapter implements ProductQueryRepository {

    private final ProductJpaRepository productJpaRepository;

    public ProductQueryRepositoryAdapter(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        return productJpaRepository.findById(productId);
    }

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll();
    }
}
