package com.app.breathe.controllers;

import com.app.breathe.entities.User;
import com.app.breathe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User loginWithGoogle(@RequestBody Map<String, String> googleData) {
        String name = googleData.get("name");
        String email = googleData.get("email");
        String profilePicture = googleData.get("profilePicture");
        return userService.saveGoogleUser(name, email, profilePicture);
    }
}
