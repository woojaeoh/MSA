package com.example.demo.member.application.exception;

public class DuplicatePhoneException extends RuntimeException {
    public DuplicatePhoneException(String phone) {
        super("이미 사용 중인 전화번호입니다: " + phone);
    }
}
