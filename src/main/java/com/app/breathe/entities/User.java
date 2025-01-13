package com.app.breathe.entities;

import com.google.firebase.database.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


public class User {
    @Id
    private String uid;

    @NotNull
    private String name;

    @NotNull
    private String email;

    private String profilePicture;

    @CreatedDate
    private LocalDateTime createdAt;

    @NotNull
    private String role;

    public User (){

    }

    public User(String uid, String name, String email, String profilePicture, LocalDateTime createdAt, String role) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.profilePicture = profilePicture;
        this.role = role;
        this.createdAt = createdAt;
    }

    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
