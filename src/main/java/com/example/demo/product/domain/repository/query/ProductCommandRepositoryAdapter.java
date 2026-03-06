package com.example.demo.product.domain.repository.query;

import com.example.demo.product.adapter.out.persistence.ProductJpaRepository;
import com.example.demo.product.domain.Product;
import com.example.demo.product.domain.repository.command.ProductCommandRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductCommandRepositoryAdapter implements ProductCommandRepository {

    private final ProductJpaRepository productJpaRepository;

    public ProductCommandRepositoryAdapter(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    public Product save(Product product){
        return productJpaRepository.save(product);
    }

    public Optional<Product> findById(UUID productId){
        return productJpaRepository.findById(productId);
    }

    public void delete(Product product){
        productJpaRepository.delete(product);
    }

}
