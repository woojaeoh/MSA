package com.example.demo.member.domain.model.infra.persistence;

import com.example.demo.member.application.domain.repository.MemberRepository;
import com.example.demo.member.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepoAdapter implements MemberRepository {

    public final MemberJpaRepository memberJpaRepository;


    public List<Member> findAll() {
        return memberJpaRepository.findAll();
    }

    public Member save(Member member) {
        return memberJpaRepository.save(member);
    }

    @Override
    public Optional<Member> findByPhone(String phone) {
        return memberJpaRepository.findByPhone(phone);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberJpaRepository.findByEmail(email);
    }
}
