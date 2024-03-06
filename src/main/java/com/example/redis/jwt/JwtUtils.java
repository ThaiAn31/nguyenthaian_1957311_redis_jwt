package com.example.redis.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtils {
    private static final String SECRET_KEY = "Employee";

    public static String generateToken(String userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000); // Token hết hạn sau 1 giờ

        return JWT.create()
                .withSubject(userId)
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public static String getUserIdFromToken(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token);

        return jwt.getSubject();
    }
}
