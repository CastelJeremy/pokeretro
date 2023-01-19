package net.pokeretro.auth.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @CrossOrigin()
    @GetMapping("/test")
    public boolean listeOeuf() {
        return true;
    }
}
