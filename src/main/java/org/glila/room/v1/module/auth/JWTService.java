package org.glila.room.v1.module.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.Password;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTService {

    /**
     * Generate token
     *
     * @param username the subject
     * @return JWT string
     */
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + getTTL() * 60 * 1000L); // TTL in ms

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getKey(), Jwts.SIG.HS256)
                .compact();
    }

    /**
     * Decode the given JWT
     *
     * @param token the JWT
     * @return username from JWT subject
     */
    public String decode(String token) {
        try {

           String username = Jwts.parser().
                   verifyWith(this.getKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .getSubject();

            //OK, we can trust this JWT
            return username;
        } catch (JwtException e) {
            //don't trust the JWT!
            System.out.println("JWT EXCEPTION : " + e.getMessage());
        }

        return null;
    }

    /**
     * TTL in minutes
     */
    private int getTTL() {
        return 60 * 24 * 7; // 7 days
    }

    private SecretKey getKey() {

        // i will move the key to .env later
        return Keys.hmacShaKeyFor(
                "kqhsdkzejzgjhaaoicnzroioagzejfxgnj".getBytes()
        );
    }
}
