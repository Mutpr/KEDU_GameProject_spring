package com.kedu.game.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.security.Security;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;

import static javax.xml.crypto.dsig.Transform.BASE64;

public class UserServiceImpl implements InitializingBean {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String Authority_key = "Auth";
    String secret;
    long tokenValidity;
    Key key;

    public UserServiceImpl(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidity){
        this.secret = secret;
        this.tokenValidity = tokenValidity * 1000;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidity);
        return Jwts.builder()
                .claim(Authority_key, authorities)
                .compact();
    }

/*    public Authentication getAuthentication(String token){

    }*/
}
