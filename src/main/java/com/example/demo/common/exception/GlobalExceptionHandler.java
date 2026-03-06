package com.example.demo.common.exception;

import com.example.demo.product.application.exception.ProductNotFoundException;
import com.example.demo.product.application.exception.SellerNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//import static jdk.internal.classfile.Classfile.build;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException ex,
                                                               HttpServletRequest request) {
        //return build(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(SellerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSellerNotFound(SellerNotFoundException ex,
                                                              HttpServletRequest request) {
      //  return build(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
    }

}
