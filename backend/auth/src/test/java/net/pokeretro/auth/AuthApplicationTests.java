package net.pokeretro.auth;

import net.pokeretro.auth.security.TokenManager;
import net.pokeretro.auth.user.User;
import net.pokeretro.auth.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        String token = tokenManager.createToken("test", "test");
        System.out.println(token);
        System.out.println(tokenManager.parseToken(token));
        assertThat(tokenManager.isTokenValid(token)).isEqualTo(0);
    }

}
