package com.example.demo.member.presentation.dto.req;

public record MemberJoinReq(
        String email,
        String name,
        String password,
        String phone,
        String address
        //추가하자면 status
) {
}
