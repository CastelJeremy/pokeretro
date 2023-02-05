package net.pokeretro.auth.token;

import com.google.gson.JsonObject;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import net.pokeretro.auth.exception.InvalidTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class TokenService {
    @Autowired
    private TokenBlacklist tokenBlacklist;

    private final String secret = "WowThatAVerySecretPasscodeThatYouWillNeverBeAbleToKnowEheh";
    private final String issuer = "auth_service";

    private final long expirationDelay = 1L;
    private final TemporalUnit expirationUnit = ChronoUnit.HOURS;

    private final Key key;

    public TokenService() {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Token create(String username) {
        Token token = new Token(
                Jwts.builder()
                        .setIssuer(issuer)
                        .setSubject(username)
                        .setIssuedAt(Date.from(Instant.now()))
                        .setExpiration(Date.from(Instant.now().plus(expirationDelay, expirationUnit)))
                        .signWith(
                                key,
                                SignatureAlgorithm.HS256)
                        .compact());

        return token;
    }

    public Jws<Claims> parseToken(String token)
            throws ExpiredJwtException,
            io.jsonwebtoken.security.SignatureException,
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
     * @return un code de retour
     *         (-1=UNKNOWN_ERROR,0=OK,1=EXPIRED,2=WRONG_SIGNATURE,3=UNSUPPORTED_TOKEN,4=MALFORMED_TOKEN,5=ILLEGAL_ARGUMENT)
     */
    public int isTokenValid(String token) {
        try {
            Jws<Claims> jwt = parseToken(token);
            if (isTokenExpired(token))
                throw new ExpiredJwtException(jwt.getHeader(), jwt.getBody(), "Expired");
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

    public boolean isValid(Token token) {
        parseToken(token.getToken());

        return true;
    }

    public void isActive(Token token) throws InvalidTokenException {
        if (isValid(token) && !isBlacklisted(token)) {
            return;
        }

        throw new InvalidTokenException("");
    }

    public Token refresh(Token token) throws InvalidTokenException {
        if (isValid(token) && !isBlacklisted(token)) {
            String username = parseToken(token.getToken()).getBody().getSubject();

            addTokenToBlacklist(token.getToken());

            return create(username);
        }

        throw new InvalidTokenException("");
    }

    public void addTokenToBlacklist(final String token) {
        clean();
        tokenBlacklist.save(new Token(token));
    }

    private void clean() {
        tokenBlacklist.deleteAll(tokenBlacklist.findAll()
                .stream()
                .filter(token -> getTokenExpiration(token).before(Date.from(Instant.now())))
                .collect(Collectors.toList()));
    }

    public boolean isBlacklisted(Token token) {
        return tokenBlacklist.findById(token.getToken()).isPresent();
    }

    public boolean isTokenExpired(final String token) {
        return tokenBlacklist.findById(token).isPresent();
    }

    public Date getTokenExpiration(final Token token) {
        try {
            return parseToken(token.toString()).getBody().getExpiration();
        } catch (ExpiredJwtException e) {
            return Date.from(Instant.now().minus(1L, ChronoUnit.DAYS));
        }
    }

    public JsonObject toJSON(String token) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("token", token);
        return jsonObject;
    }

}
