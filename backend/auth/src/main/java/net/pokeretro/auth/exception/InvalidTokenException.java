package net.pokeretro.auth.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.UNAUTHORIZED,
        reason = "Invalid token : "
)
public class InvalidTokenException extends Exception {

    public InvalidTokenException(String reason) {
        super(reason);
    }

    public InvalidTokenException(int reason) {
        super(
                switch (reason) {
                    case 1 -> "Expired JWT";
                    case 2 -> "Signature problem";
                    case 3 -> "Unsupported JWT";
                    case 4 -> "Malformed JWT";
                    case 5 -> "Illegal Argument";
                    default -> "Unknown Exception";
                }
        );
    }
}
