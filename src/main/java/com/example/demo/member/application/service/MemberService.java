package com.example.demo.member.application.service;

import com.example.demo.member.application.domain.repository.MemberRepository;
import com.example.demo.member.application.exception.DuplicatePhoneException;
import com.example.demo.member.application.usecase.MemberUseCase;
import com.example.demo.member.domain.model.Member;
import com.example.demo.member.presentation.dto.req.MemberJoinReq;
import com.example.demo.member.presentation.dto.res.MemberAdmRes;
import com.example.demo.member.presentation.dto.res.MemberRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        if (memberRepository.findByPhone(req.phone()).isPresent()) {
            throw new DuplicatePhoneException(req.phone());
        }


        Member member = Member.create(req.email(), req.name(), req.password(), req.phone(), req.address());
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
