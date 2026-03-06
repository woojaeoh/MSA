package com.example.demo.member.application.usecase;

import com.example.demo.member.presentation.dto.req.MemberJoinReq;
import com.example.demo.member.presentation.dto.res.MemberAdmRes;
import com.example.demo.member.presentation.dto.res.MemberRes;
import java.util.List;


public interface MemberUseCase {

    List<MemberRes> findAll();
    List<MemberAdmRes> findAdmAll();
    MemberRes join(MemberJoinReq req);

}
