package com.miniproject.warehouse.util;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    @Value("${jwt.secret}")
    private String key;
    @Value("${jwt.expiration}")
    private Long tokenValidity;
    private final String appName = "WarehouseProject";

    public String generateToken(String username, String role) throws NoSuchAlgorithmException {
        byte[] decodedKey = Base64.getDecoder().decode(key);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES-192");

        log.info("Generate token for {} with role {} secret: {} validity: {}", username, role, originalKey, tokenValidity);
        long nowMillis = System.currentTimeMillis();
        log.info("current millis: {}", nowMillis);
        long expMillis = nowMillis + tokenValidity;
        Date expirationDate = new Date(expMillis);
        log.info("expiration date: {}", expirationDate);

        String jws = Jwts.builder()
                .setSubject(appName)
                .setExpiration(expirationDate)
                .claim("username", username)
                .claim("role",role)
                .signWith(originalKey).compact();
        log.info("Token : {}", jws);
        return jws;
    }
}
