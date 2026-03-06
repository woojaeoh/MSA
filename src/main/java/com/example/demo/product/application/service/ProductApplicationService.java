package com.example.demo.product.application.service;

import com.example.demo.product.application.event.ProductCreatedEvent;
import com.example.demo.product.application.event.ProductDeletedEvent;
import com.example.demo.product.application.event.ProductUpdatedEvent;
import com.example.demo.product.application.exception.ProductNotFoundException;
import com.example.demo.product.application.port.in.ProductUseCase;
import com.example.demo.product.domain.Product;
import com.example.demo.product.domain.repository.ProductRepository;
import com.example.demo.product.dto.CreateProductRequest;
import com.example.demo.product.dto.UpdateProductRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ProductApplicationService implements ProductUseCase {

    private final ProductRepository productRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public ProductApplicationService(ProductRepository productRepository,
                                     ApplicationEventPublisher applicationEventPublisher) {
        this.productRepository = productRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    @Transactional
    public Product create(CreateProductRequest request, UUID actorId) {
        Product product = Product.create(
                UUID.fromString(request.sellerId()),
                request.name(),
                request.description(),
                request.price(),
                request.stock(),
                request.status(),
                actorId
        );
        Product saved = productRepository.save(product);
        applicationEventPublisher.publishEvent(new ProductCreatedEvent(saved.getId(), actorId));
        return saved;
    }

    @Override
    public Product getById(UUID productId) {
        return findByIdOrThrow(productId);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product update(UUID productId, UpdateProductRequest request, UUID actorId) {
        Product product = findByIdOrThrow(productId);
        product.update(
                request.name(),
                request.description(),
                request.price(),
                request.stock(),
                request.status(),
                actorId
        );
        applicationEventPublisher.publishEvent(new ProductUpdatedEvent(product.getId(), actorId));
        return product;
    }

    @Override
    @Transactional
    public void delete(UUID productId) {
        Product product = findByIdOrThrow(productId);
        productRepository.delete(product);
        applicationEventPublisher.publishEvent(new ProductDeletedEvent(productId, product.getModifyId()));
    }

    private Product findByIdOrThrow(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }
}