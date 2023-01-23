package net.pokeretro.auth.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenBlacklist extends JpaRepository<Token, String> {

}
