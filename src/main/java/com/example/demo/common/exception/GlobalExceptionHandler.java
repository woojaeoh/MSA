package com.example.demo.common.exception;

import com.example.demo.product.application.exception.ProductNotFoundException;
import com.example.demo.product.application.exception.SellerNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException ex,
                                                               HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage(), request.getRequestURI())
        );
    }

    @ExceptionHandler(SellerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSellerNotFound(SellerNotFoundException ex,
                                                              HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage(), request.getRequestURI())
        );
    }

}
