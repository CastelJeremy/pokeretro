package net.pokeretro.auth.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u.id FROM User u WHERE u.username=:username")
    Optional<User> findIdByUsername(@Param("username") String username);

    @Query("SELECT u.id, u.username, u.password FROM User u")
    Collection<User> getUsers();

    //Optional<String> connect(@Param("username") String username, @Param("password") String password);
    //Optional<Boolean> disconnect(@Param("username") String username);
}