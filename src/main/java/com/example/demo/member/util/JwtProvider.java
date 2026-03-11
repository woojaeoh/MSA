package com.example.demo.member.util;


import io.jsonwebtoken.*;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

import java.security.spec.X509EncodedKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;


@Component
@Slf4j
public class JwtProvider {

    @Value("${jwt.private-key}")
    private String privatekey;

    @Value("${jwt.public-key}")
    private String publickey;


    private static final long JWT_EXPIRATION_MS = 1000*60*60;
    private static final long JWT_REFRESH_EXPIRATION_MS = 86400000L * 7;

    public String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + JWT_EXPIRATION_MS);
 //       log.info("tokenSecret {}", tokenSecret);
        return Jwts.builder().subject((String) authentication.getPrincipal())
                .issuedAt(now)
                .expiration(expireDate)
                .signWith(loadPrivateKey(), Jwts.SIG.RS256)
//                .signWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(tokenSecret)), Jwts.SIG.HS512)
                .compact();
    }

    public Jws<Claims> validateToken(String token) {
        try {//파서를 빌드한것임.
            return Jwts.parser()
                    .verifyWith(loadPublicKey())
                    .build().parseSignedClaims(token);
            // 정상적인 토큰인지 체크
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT: {}", e.getMessage());
            throw new JwtException("Expired");
        }
        catch (JwtException e) {
            log.error("JWT error: {}", e.getMessage());
            throw new JwtException("JWT error");
        }
    }

    public KeyPair makeRsaKey() {

        KeyPairGenerator generator = null;
        try {
            generator = KeyPairGenerator.getInstance("RSA");

        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }

        KeyPair pa = generator.generateKeyPair();
//        String privateKey = new String(Base64.getEncoder().encode(pa.getPrivate().getEncoded()));
//        String publicKey = new String(Base64.getEncoder().encode(pa.getPublic().getEncoded()));
        log.info("privateKey = {}",new String(Base64.getEncoder().encode(pa.getPrivate().getEncoded())));
        log.info("publicKey = {}",new String(Base64.getEncoder().encode(pa.getPublic().getEncoded())));

        return pa;
    }




    private PublicKey loadPublicKey() {
        try {
            byte[] publicBytes = Base64.getDecoder().decode(publickey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
            return KeyFactory.getInstance("RSA").generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalStateException("Invalid public key", e);
        }
    }

    private PrivateKey loadPrivateKey() {
        try {
            byte[] privateBytes = Base64.getDecoder().decode(privatekey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec (privateBytes);
            return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalStateException("Invalid public key", e);
        }
    }

}