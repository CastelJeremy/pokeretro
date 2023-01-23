package net.pokeretro.auth;

import net.pokeretro.auth.security.PasswordHash;
import net.pokeretro.auth.token.TokenService;
import net.pokeretro.auth.user.User;
import net.pokeretro.auth.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuthApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @Test
    void createUser() {
        User newUser = new User("test4", "password");
        User savedUser = userService.saveUser(newUser);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    void createToken() {
        String token = tokenService.createToken("test");
        System.out.println(token);
        System.out.println(tokenService.parseToken(token));
        assertThat(tokenService.isTokenValid(token)).isEqualTo(0);
    }

    @Test
    void destroyToken() {
        String token = tokenService.createToken("test");

        tokenService.addTokenToBlacklist(token);

        assertThat(tokenService.isTokenValid(token)).isEqualTo(1);
    }

    @Test
    void hashPassword() {
        String encodedPassword;
        try {
            encodedPassword = PasswordHash.hash("JeSuisUnMotDePasse");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        System.out.println(encodedPassword);

        assertThat(encodedPassword).isNotNull();
    }

}
