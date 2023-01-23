package net.pokeretro.auth.token;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
