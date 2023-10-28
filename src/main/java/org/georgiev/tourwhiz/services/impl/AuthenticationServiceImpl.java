package org.georgiev.tourwhiz.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.georgiev.tourwhiz.data.models.User;
import org.georgiev.tourwhiz.security.TourWhizUserPrincipal;
import org.georgiev.tourwhiz.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${api.secret}")
    private String secretKey;

    @Override
    public String generateToken(User user) {
        Instant expirationTime = Instant.now().plus(1, ChronoUnit.HOURS);
        Date expirationDate = Date.from(expirationTime);

        String token = Jwts.builder()
                .claim("id", user.getId())
                .claim("sub", user.getUsername())
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)), SignatureAlgorithm.HS512)
                .compact();

        return "Bearer " + token;
    }

    @Override
    public TourWhizUserPrincipal parseToken(String token) {

        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)))
                .build()
                .parseClaimsJws(token);

        String username = claimsJws.getBody().getSubject();
        Long id = claimsJws.getBody().get("id", Long.class);

        return new TourWhizUserPrincipal(id, username);
    }

    @Override
    public TourWhizUserPrincipal getCurrentPrincipal() {

        return (TourWhizUserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
    }
}
