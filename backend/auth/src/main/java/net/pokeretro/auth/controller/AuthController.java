package net.pokeretro.auth.controller;

import net.pokeretro.auth.exception.InvalidTokenException;
import net.pokeretro.auth.security.TokenManager;
import net.pokeretro.auth.user.User;
import net.pokeretro.auth.user.UserRepository;
import net.pokeretro.auth.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    @Autowired
    private UserService service;
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin()
    @GetMapping("/test")
    public boolean test() {
        return true;
    }

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public Collection<User> getUser(@RequestParam(value="id", required = false, defaultValue = "-1") long id,
                        @RequestParam(value="username", required = false, defaultValue = "-1") String username) {
        if(id >= 0) {
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
    public String createAccount(@RequestParam(value="username") String username,
                           @RequestParam(value="password") String password) {
        service.saveUser(new User(username, password));
        return TokenManager.getInstance().createToken(username, password);
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String connect(@RequestParam(value="username") String username,
                           @RequestParam(value="password") String password) {
        Collection<User> users = getUser(-1, username);
        if(users.size() > 0) {
            for (User user : users) {
                //TODO chercher si le mot de passe est correct.
                if(true) {
                    return TokenManager.getInstance().createToken(username, password);
                }
            }
            throw new RuntimeException("Wrong password !");
        } else {
            throw new RuntimeException("The user " + username + " does not exists !");
        }
    }

    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public Integer disconnect(@RequestParam(value="username") String username) {
        Collection<User> users = getUser(-1, username);
        if(users.size() > 0) {
            //TODO chercher le JWT de l'utilisateur et le rendre "EXPIRÉ"
            throw new RuntimeException("Not yet implemented !");
        } else {
            throw new RuntimeException("The user " + username + " does not exists !");
        }
    }

    @RequestMapping(value="validate", method = RequestMethod.POST)
    public void validateToken(@RequestParam(value="token") String token) throws InvalidTokenException {
        int response = TokenManager.getInstance().isTokenValid(token);

        if(response != 0) {
            throw new InvalidTokenException(response);
        }
    }
}
