package net.pokeretro.auth.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

public class TokenManager {

    private static TokenManager tokenManager;

    //@Value("${app.jwt.secret}")
    private final String secret = "WowThatAVerySecretPasscodeThatYouWillNeverBeAbleToKnowEheh";
    //@Value("${app.jwt.issuer}")
    private final String issuer = "auth_service";

    private final long expirationDelay;
    private final TemporalUnit expirationUnit;

    private final Key key;

    private TokenManager(long expirationDelay, TemporalUnit expirationUnit) {
        this.expirationDelay = expirationDelay;
        this.expirationUnit = expirationUnit;
        key = Keys.hmacShaKeyFor(secret.getBytes());
        //key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public static TokenManager getInstance(long expirationDelay, TemporalUnit expirationUnit) {
        return tokenManager == null ? new TokenManager(expirationDelay, expirationUnit) : tokenManager;
    }

    /**
     * Par défaut, le temps d'expiration du token est 1 heure.
     */
    public static TokenManager getInstance() {
        return getInstance(1L, ChronoUnit.HOURS);
    }

    public String createToken(String username, String password) {
        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
                //.setExpiration(Date.from(Instant.now().plus(expirationDelay, expirationUnit)))
                .claim("end_date", System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7))
                .signWith(
                        key,
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    public Jws<Claims> parseToken(String token)
            throws ExpiredJwtException,
            SignatureException,
            UnsupportedJwtException,
            MalformedJwtException,
            IllegalArgumentException {
        return Jwts.parserBuilder()
                .requireIssuer(issuer)
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    /**
     *
     * @param token est le token à valider
     * @return un code de retour (-1=UNKNOWN_ERROR,0=OK,1=EXPIRED,2=WRONG_SIGNATURE,3=UNSUPPORTED_TOKEN,4=MALFORMED_TOKEN,5=ILLEGAL_ARGUMENT)
     */
    public int isTokenValid(String token) {
        try {
            parseToken(token);
            return 0;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            return 1;
        } catch (SignatureException e) {
            e.printStackTrace();
            return 2;
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
            return 3;
        } catch (MalformedJwtException e) {
            e.printStackTrace();
            return 4;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return 5;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
