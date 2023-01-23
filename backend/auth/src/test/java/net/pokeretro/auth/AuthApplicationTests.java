package net.pokeretro.auth;

import net.pokeretro.auth.security.PasswordHash;
import net.pokeretro.auth.security.TokenManager;
import net.pokeretro.auth.user.User;
import net.pokeretro.auth.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

import static net.pokeretro.auth.security.PasswordHash.hash;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuthApplicationTests {

    @Autowired
    private UserService service;

    @Test
    void createUser() {
        User newUser = new User("test4", "password");
        User savedUser = service.saveUser(newUser);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    void createToken() {
        TokenManager tokenManager = TokenManager.getInstance();
        String token = tokenManager.createToken("test");
        System.out.println(token);
        System.out.println(tokenManager.parseToken(token));
        assertThat(tokenManager.isTokenValid(token)).isEqualTo(0);
    }

    @Test
    void hashPassword() {
        String encodedPassword = null;
        try {
            encodedPassword = PasswordHash.hash("JeSuisUnMotDePasse");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        System.out.println(encodedPassword);

        assertThat(encodedPassword).isNotNull();
    }

}
