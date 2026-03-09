package com.example.demo.payment.presentation;

import com.example.demo.payment.application.PaymentService;
import com.example.demo.payment.application.dto.PaymentFailureInfo;
import com.example.demo.payment.application.dto.PaymentInfo;
import com.example.demo.payment.presentation.dto.PaymentFailRequest;
import com.example.demo.payment.presentation.dto.PaymentRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

//    @GetMapping
//    public ResponseEntity<List<PaymentInfo>> findAll(Pageable pageable){
//        return paymentService.findAll(pageable);
//    }

    @PostMapping("/confirm")
    @Operation(summary = "토스 결제 승인", description = "토스 결제 완료 후 paymentKey/orderId/amount를 전달받아 결제를 승인한다.")
    public ResponseEntity<PaymentInfo> confirm(@RequestBody PaymentRequest request){
        return paymentService.confirm(request.toCommand());
    }

    @Operation(summary = "결제 실패 기록", description = "토스 결제 실패 정보를 저장한다.")
    @PostMapping("/fail")
    public ResponseEntity<PaymentFailureInfo> fail (@RequestBody PaymentFailRequest request){
        return paymentService.recordFailure(request.toCommand());
    }


}
