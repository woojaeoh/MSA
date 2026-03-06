package com.example.demo.product.adapter.out.persistence;

import com.example.demo.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<Product, UUID> {// 기본적으로 id를 가지고 값을 가져옴.
}
