package com.leticia.api.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JWTProvider {

    @Value("${security.token.secret}")
    private String secretKey;

    public Map<String, Object> validateToke(String token) {
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        try {
            DecodedJWT jwt = JWT.require(algorithm).build().verify(token);
            String subject = jwt.getSubject();

            List<String> roles = jwt.getClaim("roles").asList(String.class);
            Map<String, Object> claims = new HashMap<>();
            claims.put("subject", subject);
            claims.put("roles", roles);
            return  claims;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }
}
