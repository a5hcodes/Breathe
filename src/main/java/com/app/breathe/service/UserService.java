package com.app.breathe.service;

import com.app.breathe.entities.User;
import com.app.breathe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User saveGoogleUser(String name, String email, String profilePicture) {
        Optional<User> existingUser= Optional.ofNullable(userRepository.findByEmail(email));
        if(existingUser.isPresent()) {
            return existingUser.get();
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setProfilePicture(profilePicture);
        user.setCreatedAt(LocalDateTime.now());
        user.setRole("USER");
        return userRepository.save(user);

    }
}
