package com.example.demo.member.application.domain.repository;

import com.example.demo.member.domain.model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    List<Member> findAll();

    Member save(Member member);

    Optional<Member> findByPhone(String phone);

    Optional<Member> findByEmail(String email);

}
