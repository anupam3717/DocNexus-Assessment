package com.smart.DocNexusAssessment.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    private String secret_key="KaPdSgUkXp2s5v8y/B?E(H+MbQeThWmY";

    public String generateToken(String email){
        Long jwtExpiration = 3600000L;
        Key key= Keys.hmacShaKeyFor(secret_key.getBytes());
        Date currentDate = new Date();
        Date expiration = new Date(currentDate.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expiration)
                .setIssuedAt(currentDate)
                .signWith(key)
                .compact();

    }

    private Claims parseToken(String token){
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(secret_key.getBytes())
                .build();
        try {
            Claims x = jwtParser.parseClaimsJws(token)
                    .getBody();
            return x;
        }catch (Exception e){
          return null;
        }

    }

    public boolean isTokenValid(String token){
        return parseToken(token)!=null;
    }

    public String getUserNameFromToken(String token){
        Claims claims=parseToken(token);
        return claims.getSubject();
    }
}
