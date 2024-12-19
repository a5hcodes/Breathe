package com.app.breathe.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "Users")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String profilePicture;

}
