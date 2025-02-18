package com.app.breathe.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    private static Firestore firestore;

    @Bean
    public Firestore firestore() throws IOException {
        if (firestore == null) { // Ensure Firestore is only initialized once
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase/breathe-3795c-firebase-adminsdk-m9bji-b9d4fd3d84.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase initialized successfully.");
            }

            firestore = FirestoreClient.getFirestore();
        }
        return firestore;
    }
}
