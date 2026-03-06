package com.example.demo.product.dto;

import java.math.BigDecimal;

//코틀린 생성자 문법을 따라온것. -> 생성자이면서 멤버변수 추가된것.
//레코드는 getter만 사용하기 위함.
//처음에 주입한걸 수정하지 않기 위해 사용하는것
public record CreateProductRequest(
        String sellerId,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        String status,
        String creatorId
){
}

