package net.pokeretro.auth.token;

import jakarta.persistence.*;
import net.pokeretro.auth.security.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Entity
@Table(name = "token_blacklist")
public class Token {
    @Id
    @Column(name = "token", nullable = false, length = 200)
    private String token;

    public Token() { }

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return token;
    }
}
