package net.pokeretro.auth.controller;

import net.pokeretro.auth.exception.InvalidPasswordException;
import net.pokeretro.auth.exception.InvalidTokenException;
import net.pokeretro.auth.security.PasswordHash;
import net.pokeretro.auth.token.TokenService;
import net.pokeretro.auth.user.User;
import net.pokeretro.auth.user.UserRepository;
import net.pokeretro.auth.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    @Autowired
    private UserService service;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @CrossOrigin()
    @GetMapping("/test")
    public boolean test() {
        return true;
    }

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public Collection<User> getUser(@RequestParam(value="id", required = false) UUID id,
                        @RequestParam(value="username", required = false, defaultValue = "-1") String username) {
        if(id != null) {
            //Si ID est défini : alors on renvoie le joueur ayant l'ID correspondant
            return service.getUsers()
                    .stream()
                    .filter(user -> user.getId() == id)
                    .collect(Collectors.toList());
        } else if (username != null && !username.equals("-1")) {
            //Sinon Si USERNAME est défini : alors on renvoie le joueur ayant le USERNAME correspondant
            return service.getUsers()
                    .stream()
                    .filter(user -> user.getUsername().equals(username))
                    .collect(Collectors.toList());
        } else {
            //Sinon on renvoie tous les joueurs
            return service.getUsers();
        }
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String register(@RequestParam(value="username") String username,
                           @RequestParam(value="password") String password) throws NoSuchAlgorithmException {
        service.saveUser(new User(username, PasswordHash.hash(password)));
        return tokenService.toJSON(tokenService.createToken(username)).toString();
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(@RequestParam(value="username") String username,
                           @RequestParam(value="password") String password) throws NoSuchAlgorithmException, InvalidPasswordException {
        Collection<User> users = getUser(null, username);
        if(users.size() > 0) {
            for (User user : users) {
                if(user.getPassword().equals(PasswordHash.hash(password))) {
                    return tokenService.toJSON(tokenService.createToken(username)).toString();
                }
            }
            throw new InvalidPasswordException();
        } else {
            throw new RuntimeException("The user " + username + " does not exists !");
        }
    }

    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public void logout(@RequestParam(value="token") String token) {
        tokenService.addTokenToBlacklist(token);
    }

    @RequestMapping(value="validate", method = RequestMethod.POST)
    public void validateToken(@RequestParam(value="token") String token) throws InvalidTokenException {
        int response = tokenService.isTokenValid(token);

        if(response != 0) {
            throw new InvalidTokenException(response);
        }
    }

    @RequestMapping(value="/refresh", method = RequestMethod.POST)
    public String refresh(@RequestParam(value="token") String token) throws InvalidTokenException {
        return tokenService.toJSON(tokenService.refreshToken(token)).toString();
    }
}
