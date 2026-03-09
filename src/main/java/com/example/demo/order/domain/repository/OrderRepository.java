package com.example.demo.order.domain.repository;

import com.example.demo.order.domain.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface OrderRepository {

    List<Order> findAll();

    Optional<Order> findById( UUID id);

    Order save(Order order);

    List<Order> findUnsettledPaidOrders(LocalDateTime fromInclusive, LocalDateTime toExclusive);
}
