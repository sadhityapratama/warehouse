package com.miniproject.warehouse.util;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    private SecretKey key;
    private Long tokenValidity;
    private final String appName = "WarehouseProject";

    public String generateToken(String username, String role){
        log.info("Generate token for {} with role {} secret: {} validity: {}", username, role, key.toString(), tokenValidity);
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
                .signWith(key).compact();
        log.info("Token : {}", jws);
        return jws;
    }
}
