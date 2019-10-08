package com.hotmart.api.company.services.security;

import com.hotmart.api.company.model.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${company.jwt.expiration}")
    private String expiration;

    @Value("${company.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication){

        final User userAuthenticated = (User) authentication.getPrincipal();
        final Date today = new Date();
        final Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("Api company")
                .setSubject(userAuthenticated.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
