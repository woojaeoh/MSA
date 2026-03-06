package com.example.demo.product.application.command;

import com.example.demo.product.application.event.ProductCreatedEvent;
import com.example.demo.product.application.event.ProductDeletedEvent;
import com.example.demo.product.application.event.ProductUpdatedEvent;
import com.example.demo.product.domain.Product;
import com.example.demo.product.domain.repository.command.ProductCommandRepository;
import com.example.demo.product.dto.CreateProductRequest;
import com.example.demo.product.dto.UpdateProductRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ProductCommandService implements ProductCommandUseCase {

    private final ProductCommandRepository productCommandRepository;
    private final ApplicationEventPublisher eventPublisher;

    public ProductCommandService(ProductCommandRepository productCommandRepository,
                                 ApplicationEventPublisher eventPublisher) {
        this.productCommandRepository = productCommandRepository;
        this.eventPublisher = eventPublisher;
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
        Product saved = productCommandRepository.save(product);
        eventPublisher.publishEvent(new ProductCreatedEvent(saved.getId(), actorId));
        return saved;
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
        eventPublisher.publishEvent(new ProductUpdatedEvent(product.getId(), actorId));
        return product;
    }

    @Override
    @Transactional
    public void delete(UUID productId) {
        Product product = findByIdOrThrow(productId);
        UUID actorId = product.getModifyId();
        productCommandRepository.delete(product);
        eventPublisher.publishEvent(new ProductDeletedEvent(productId, actorId));
    }

    private Product findByIdOrThrow(UUID productId) {
        return productCommandRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }
}
