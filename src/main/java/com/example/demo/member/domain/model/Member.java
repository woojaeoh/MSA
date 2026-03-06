package com.example.demo.member.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "\"member\"", schema = "public")
public class Member {
    @Schema(description = "유저의 UUID")
    @Id
    private UUID id;
    @Schema(description = "유저의 email")
    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Schema(description = "유저명")
    @Column(name = "\"name\"", length = 20)
    private String name;
    @Schema(description = "비밀번호")
    @Column(name = "\"password\"", nullable = false, length = 100)
    private String password;
    @Schema(description = "핸드폰번호")
    @Column(nullable = false, length = 20, unique = true)
    private String phone;
    @Schema(description = "유저상태")
    @Column(name = "\"status\"", length = 5)
    private String status;

    @Schema(description = "주소")
    @Column(nullable = false, length = 100)
    private String address;

    @Column(name = "reg_id", nullable = false)
    private UUID regId;

    @Column(name = "reg_dt", nullable = false)
    private LocalDateTime regDt;

    @Column(name = "modify_id", nullable = false)
    private UUID modifyId;

    @Column(name = "modify_dt", nullable = false)
    private LocalDateTime modifyDt;

    @Column(name = "saltkey", nullable = false, length = 14)
    private String saltKey;

    @Column(name = "flag", length = 5)
    private String flag;

    public static Member create(String email, String name, String password, String phone, String address) {
        Member member = new Member();
        member.email = email;
        member.name = name;
        member.password = password;
        member.phone = phone;
        member.address = address;
        member.saltKey = java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 14);
        return member;
    }

    @PrePersist
    public void onCreate() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (regId == null) {
            regId = id;
        }
        if (modifyId == null) {
            modifyId = regId;
        }
        if (regDt == null) {
            regDt = LocalDateTime.now();
        }
        if (modifyDt == null) {
            modifyDt = regDt;
        }
        if (status == null) {
            status = "ACTIVE";
        }
    }

}
