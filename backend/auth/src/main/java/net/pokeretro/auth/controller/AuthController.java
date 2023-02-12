package net.pokeretro.auth.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.pokeretro.auth.exception.InvalidTokenException;
import net.pokeretro.auth.exception.UserCreateException;
import net.pokeretro.auth.exception.UserValidateException;
import net.pokeretro.auth.token.Token;
import net.pokeretro.auth.token.TokenService;
import net.pokeretro.auth.user.User;
import net.pokeretro.auth.user.UserRepository;
import net.pokeretro.auth.user.UserService;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<Token> register(@RequestBody User user) throws NoSuchAlgorithmException {
        try {
            userService.create(user);

            return ResponseEntity.ok(tokenService.create(user.getUsername()));
        } catch (UserCreateException e) {

        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody User user) throws NoSuchAlgorithmException {
        try {
            userService.validate(user);

            return ResponseEntity.ok(tokenService.create(user.getUsername()));
        } catch (UserValidateException e) {

        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "validate", method = RequestMethod.POST)
    public void validateToken(@RequestBody Token token) {
        try {
            tokenService.isActive(token);

            return;
        } catch (InvalidTokenException e) {

        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/refresh")
    public ResponseEntity<Token> refresh(@RequestBody Token token) {
        try {
            return ResponseEntity.ok(tokenService.refresh(token));
        } catch (InvalidTokenException e) {

        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/logout")
    public void logout(@RequestBody Token token) {
        tokenService.addTokenToBlacklist(token.getToken());
    }
}
