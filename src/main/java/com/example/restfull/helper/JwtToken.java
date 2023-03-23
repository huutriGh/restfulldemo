package com.example.restfull.helper;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;


import com.example.restfull.model.Account;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
public class JwtToken {
    private static final long EXPIRATION_LIMIT_IN_MINUTES = 30;

    // The JWT signature algorithm we will be using to sign the token
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    // Keys used with HS256 MUST have a size >= 256 bits
    private static final String SECRET_KEY = "gpcoderdotcom-token-base-authentication-with-jwt-example";

    private static final String ISSUER = "https://localhost:8080";

    private JwtToken() {
        super();
    }

    public static String createJWT(Account user) {

        // Get the current time
        long currentTimeInMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeInMillis);

        // The privateKey is only valid for the next EXPIRATION_LIMIT_IN_MINUTES
        long expirationTimeInMilliSeconds = TimeUnit.MINUTES.toMillis(EXPIRATION_LIMIT_IN_MINUTES);
        Date expirationDate = new Date(currentTimeInMillis + expirationTimeInMilliSeconds);

        // Will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = Base64.getEncoder().encode(SECRET_KEY.getBytes());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());

        // Sets the JWT Claims sub (subject) value
        Claims claims = Jwts.claims().setSubject(user.getAccountName());
       // claims.put("roles", user.getRoles());

        // Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder() // Configured and then used to create JWT compact serialized strings
                .setClaims(claims).setId(UUID.randomUUID().toString()) // Sets the JWT Claims jti (JWT ID) value
                .setIssuedAt(now) // Sets the JWT Claims iat (issued at) value
                .setIssuer(ISSUER) // Sets the JWT Claims iss (issuer) value
                .setExpiration(expirationDate) // Sets the JWT Claims exp (expiration) value
                .signWith(signingKey, SIGNATURE_ALGORITHM);

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    /**
     * Get User from the given token
     */
    public static Account getAccountFromToken(String token) {
        final Claims claims = decodeJWT(token);
        Account account = new Account();
        account.setAccountName(claims.getSubject());
       // account.setRoles((List<String>) claims.get("roles"));
        return account;
    }

    /**
     * Check if the token was issued by the server and if it's not expired
     */
    public static Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private static Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = decodeJWT(token);
        return claimsResolver.apply(claims);
    }

    private static Claims decodeJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        return Jwts.parser() // Configured and then used to parse JWT strings
                .setSigningKey(Base64.getEncoder().encode(SECRET_KEY.getBytes())) // Sets the signing key used to verify
                // any discovered JWS digital signature
                .parseClaimsJws(jwt) // Parses the specified compact serialized JWS string based
                .getBody();
    }
}
