package com.setupsnap.setup_snap_api.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.Set;

@Component
public class JwtUtils {

    @Value("${app.jwt.secret}")
    private String jwtKey;

    public String generateToken(String email, Set<String> roles){
        return Jwts.builder()
                .setSubject(email)
                .claim("roles",roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+86400000))
                .signWith(SignatureAlgorithm.HS256,jwtKey)
                .compact();
    }

    public String extractEmail(String token){
        return Jwts.parser()
                .setSigningKey(jwtKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Boolean isTokenValid(String token){
        try{
            Jwts.parser()
                    .setSigningKey(jwtKey)
                    .parseClaimsJws(token);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
}
