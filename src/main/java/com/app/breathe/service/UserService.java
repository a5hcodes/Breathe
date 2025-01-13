package com.app.breathe.service;

import com.app.breathe.entities.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

//   private final DatabaseReference userRef;
//
//   public UserService() {
//       FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//       this.userRef = firebaseDatabase.getReference("Users");
//   }
//
//    public User saveGoogleUser(String name, String email, String profilePicture) {
//       String userID = generateUniqueId();
//
//        User user = new User();
//        user.setName(name);
//        user.setEmail(email);
//        user.setProfilePicture(profilePicture);
//        user.setRole("user");
//        user.setCreatedAt(LocalDateTime.now());
//
//
//        userRef.child(user.getUid()).setValueAsync(user);
//        return user;
//    }
//    private String generateUniqueId() {
//        return "user-" + System.currentTimeMillis(); // Example of generating a unique ID
//    }
}
