package com.app.breathe;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;

@SpringBootApplication
public class BreatheApplication {

    public static void main(String[] args) {
//        try {
//            FileInputStream serviceAccount = new FileInputStream("/Users/apple/IdeaProjects/Breathe/src/main/resources/breathe-3795c-firebase-adminsdk-m9bji-b9d4fd3d84.json");
//
//
//            FirebaseOptions options = FirebaseOptions.builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .setDatabaseUrl("https://breathe-3795c-default-rtdb.firebaseio.com").build();
//
//            FirebaseApp.initializeApp(options);
//            System.out.println("Firebase App started");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("Firebase App failed to start" + e.getMessage());
//        }
        SpringApplication.run(BreatheApplication.class, args);
    }

}
