package com.example.demo.product.adapter.in.web;

import com.example.demo.product.application.command.ProductCommandUseCase;
import com.example.demo.product.application.query.ProductQueryUseCase;
import com.example.demo.product.domain.Product;
import com.example.demo.product.dto.CreateProductRequest;
import com.example.demo.product.dto.UpdateProductRequest;
import com.example.demo.product.dto.out.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductCommandUseCase productCommandUseCase;
    private final ProductQueryUseCase productQueryUseCase;

    public ProductController(ProductCommandUseCase productCommandUseCase,
                             ProductQueryUseCase productQueryUseCase) {
        this.productCommandUseCase = productCommandUseCase;
        this.productQueryUseCase = productQueryUseCase;
    }

    @PostMapping
    @Operation(summary = "상품 생성", description = "신규 상품을 생성합니다.")
    @ApiResponse()
    public ResponseEntity<ProductResponse> create(@RequestBody CreateProductRequest request) {
        UUID actorId = UUID.fromString(request.creatorId());
        Product product = productCommandUseCase.create(request, actorId);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(product));
    }

    @GetMapping("/{productId}")
    public ProductResponse getById(@PathVariable UUID productId) {
        return toResponse(productQueryUseCase.getById(productId));
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return productQueryUseCase.getAll().stream().map(this::toResponse).toList();
    }

    @PutMapping("/{productId}")
    public ProductResponse update(@PathVariable UUID productId,
                                  @RequestBody UpdateProductRequest request) {
        UUID actorId = UUID.fromString(request.modifierId());
        return toResponse(productCommandUseCase.update(productId, request, actorId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable UUID productId) {
        productCommandUseCase.delete(productId);
        return ResponseEntity.noContent().build();
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getSellerId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getStatus(),
                product.getModifyDt()
        );
    }
}
