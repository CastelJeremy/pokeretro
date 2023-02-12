package net.pokeretro.auth.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.pokeretro.auth.exception.UserCreateException;
import net.pokeretro.auth.exception.UserValidateException;
import net.pokeretro.auth.security.PasswordHash;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(User user) throws UserCreateException, NoSuchAlgorithmException {
        if (user.getPassword().length() > 8 && user.getUsername().length() > 4) {
            Optional<User> optUser = userRepository.findByUsername(user.getUsername());

            if (optUser.isEmpty()) {
                User newUser = new User(user.getUsername(), PasswordHash.hash(user.getPassword()));
                userRepository.save(newUser);

                return newUser;
            }
        }

        throw new UserCreateException();
    }

    public void validate(User user) throws UserValidateException, NoSuchAlgorithmException {
        Optional<User> optUser = userRepository.findByUsername(user.getUsername());

        if (optUser.isPresent()) {
            User existingUser = optUser.get();
            String inputPassHash = PasswordHash.hash(user.getPassword());

            if (existingUser.getPassword().equals(inputPassHash)
                    && existingUser.getUsername().equals(user.getUsername())) {
                return;
            }
        }

        throw new UserValidateException();
    }
}
