package net.pokeretro.auth.token;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenBlacklist extends JpaRepository<Token, String> {

}
