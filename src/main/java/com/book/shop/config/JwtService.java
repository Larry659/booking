package com.book.shop.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {
//    @Value("${SECRET}")
//    private static String SECRET_KEY;

    //private static final String SECRET_KEY ="T4bV5qsMq6mY7yuU2+aO02hNMOQhvHDuJ0jutzEW9oTEwm+3EsRsp1vsvRjPxl35XXAg6H5/GJiwWkO4ogIi3PfVeepy02pzcuJNsN+zZwkVc7D2xRS2JXdO3Ww2+SgoGotaj2mLoPNImXbGkBeNYKwHWqb75KG9ME6ivNj/QbNfk4bxxcPDMeqyFhwza4cuvYla+6eZdy/H3i8M1PgYa+rPo00Y5AyFj6I3P2wW2O7utQ7H+2ZykbJ3rF8Ks5pNlLh5Ffs9iqHjLSIxVI2ASyVvzxtuYpMSpAm4FPU+yROgeAvWfw89RVbj8cne0U6e2ufu4Us+GBJ0d7bOSGnviXZExPzM2uSci2BwcjtrUi8=" ;
//https://generate-random.org/encryption-key-generator
private static final String SECRET_KEY =  "CogpbEqBIrwd2Deiy1B+bHHEoMshtqZu+OBskAoo0YGftYYNpdVWdyVvowm9QAqg";


    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim (String token, Function<Claims, T> claimsResolver){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims (String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUserEmail(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken (UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken (HashMap<String, Object> claims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000*60*60*24)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
