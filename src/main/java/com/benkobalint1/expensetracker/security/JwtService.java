    package com.benkobalint1.expensetracker.security;

    import io.jsonwebtoken.Claims;
    import io.jsonwebtoken.Jwts;
    import io.jsonwebtoken.security.Keys;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Component;

    import javax.crypto.SecretKey;
    import java.nio.charset.StandardCharsets;
    import java.util.Date;

    /**
     * @author benkobalint1
     **/
    @Component
    public class JwtService {

        private final SecretKey secretKey;
        private final long expirationMs;

        public JwtService(
                @Value("${jwt.secret}") String secret,
                @Value("${jwt.expiration-ms}") long expirationMs) {
            this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            this.expirationMs = expirationMs;
        }

        public String generateToken(Long userId, String email) {
            return Jwts.builder()
                    .subject(email)
                    .claim("UserId", userId)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + expirationMs))
                    .signWith(secretKey)
                    .compact();
        }

        public Claims extractClaims(String token) {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }

        public String extractEmail(String token) {
            return extractClaims(token).getSubject();
        }

        public boolean isTokenValid(String token) {
            try {
                extractClaims(token);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
