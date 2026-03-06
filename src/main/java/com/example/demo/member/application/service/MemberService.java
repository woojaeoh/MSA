package com.example.demo.member.application.service;

import com.example.demo.member.application.domain.repository.MemberRepository;
import com.example.demo.member.application.exception.DuplicatePhoneException;
import com.example.demo.member.application.usecase.MemberUseCase;
import com.example.demo.member.domain.model.Member;
import com.example.demo.member.presentation.dto.req.MemberJoinReq;
import com.example.demo.member.presentation.dto.res.MemberAdmRes;
import com.example.demo.member.presentation.dto.res.MemberRes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
@Transactional(readOnly = true) //변수가 바껴도, 데이터베이스 값이 바뀌면 안되기 때문.
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {

    public final MemberRepository memberRepository;

    @Override
    public List<MemberRes> findAll(){
        return memberRepository.findAll().stream().map(this::changeMemberResType).toList();
    }

    @Override
    public List<MemberAdmRes> findAdmAll(){
        return memberRepository.findAll().stream().map(this::changeMemberAdmResType).toList();
    }

    private MemberRes changeMemberResType(Member member){
        return new MemberRes(
                member.getId(), member.getName(), member.getAddress()
        );
    }

    @Override
    @Transactional
    public MemberRes join(MemberJoinReq req) {

        // 1. 전화번호 중복 체크
        if (memberRepository.findByPhone(req.phone()).isPresent()) {
            throw new DuplicatePhoneException(req.phone());
        }

        // 2. saltKey 생성 (14자리)
        SecureRandom random = new SecureRandom();
        String saltKey = Base64.getEncoder().encodeToString(random.generateSeed(16)).substring(0, 14);

        // 3. 비밀번호 암호화 (password + saltKey)
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(req.password() + saltKey);

        // 4. Member 생성 → saltKey 덮어쓰기
        Member member = Member.create(req.email(), req.name(), encodedPassword, req.phone(), req.address());
        member.setSaltKey(saltKey);

        Member saved = memberRepository.save(member);
        return changeMemberResType(saved);
    }

    private MemberAdmRes changeMemberAdmResType(Member member){
        return new MemberAdmRes(
                member.getId(), member.getEmail(), member.getName(), member.getPhone(),
                member.getAddress(), member.getStatus(), member.getRegId(), member.getRegDt(),
                member.getModifyId(), member.getModifyDt(), member.getFlag()
        );
    }



}
