package com.example.demo.member.domain.model.presentation.controller;

import com.example.demo.member.application.usecase.MemberUseCase;
import com.example.demo.member.presentation.dto.req.Login;
import com.example.demo.member.presentation.dto.req.MemberJoinReq;
import com.example.demo.member.presentation.dto.res.MemberAdmRes;
import com.example.demo.member.presentation.dto.res.MemberRes;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/member")
@Tag(name = "Member", description = "사용자 CRUD API")
@RequiredArgsConstructor
public class MemberController {
    public final MemberUseCase memberUseCase;

    @GetMapping("findAll")
    public ResponseEntity<List<MemberRes>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(memberUseCase.findAll());
    }

    @GetMapping("findAdmAll")
    public ResponseEntity<List<MemberAdmRes>> getAdmAll(){
        return ResponseEntity.status(HttpStatus.OK).body(memberUseCase.findAdmAll());
    }

    @PostMapping("join")
    public ResponseEntity<MemberRes> join(@RequestBody MemberJoinReq req){
        return ResponseEntity.status(HttpStatus.CREATED).body(memberUseCase.join(req));
    }

    @PostMapping("login")
    public ResponseEntity<Boolean> login(@RequestBody Login login){
        return ResponseEntity.status(HttpStatus.CREATED).body(memberUseCase.login(login));
    }
}
