package com.shingu.university.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private Key secretKey;
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24시간

    @PostConstruct
    public void init() {
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // HS256 키 생성
    }

    public String createToken(Integer userId, String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(String.valueOf(userId)) // ✅ 사용자 ID를 sub로
                .claim("email", email)              // ✅ 이메일은 claim으로
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }
}
