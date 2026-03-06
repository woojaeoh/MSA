package com.example.demo.product.infrastructure.event;

import com.example.demo.product.application.event.ProductCreatedEvent;
import com.example.demo.product.application.event.ProductDeletedEvent;
import com.example.demo.product.application.event.ProductUpdatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ProductEventHandler {

    private static final Logger log  = LoggerFactory.getLogger(ProductEventHandler.class);

    @Async
    @EventListener
    public void on(ProductCreatedEvent event){
        log.info("productCreatedEvent:{}, actorId ={} " , event.productId(), event.actorId());
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT) // 커밋된 이후에 무언가를 하겠다.
    public void handle(ProductCreatedEvent event) {
        log.info("ProductCreatedEvent:{}, actorId ={} " , event.productId(), event.actorId());
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(ProductUpdatedEvent event) {
        log.info("ProductUpdatedEvent:{}, actorId ={} " , event.productId(), event.actorId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(ProductDeletedEvent event){
        log.info("ProductDeleteEvent:{}, actorId ={} " , event.productId(), event.actorId());
    }
}
