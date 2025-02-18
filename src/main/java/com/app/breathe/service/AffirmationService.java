package com.app.breathe.service;

import com.app.breathe.entities.Affirmation;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AffirmationService {

    private final Firestore firestore;
    private static final String COLLECTION_NAME = "affirmations";
    private static final Logger LOGGER = Logger.getLogger(AffirmationService.class.getName());
    private static final Random RANDOM = new Random();

    public AffirmationService(Firestore firestore) {
        this.firestore = firestore;
    }

    public Affirmation getRandomAffirmationByEmotion(String emotion) {
        try {
            // Query Firestore for affirmations matching the emotion
            ApiFuture<QuerySnapshot> future = firestore.collection(COLLECTION_NAME)
                    .whereEqualTo("emotion", emotion)
                    .get();

            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            if (documents.isEmpty()) {
                LOGGER.warning("No affirmations found for emotion: " + emotion);
                return null;
            }

            // Select a random document
            QueryDocumentSnapshot randomDoc = documents.get(RANDOM.nextInt(documents.size()));

            // Extract data properly
            String aid = randomDoc.getId();
            String audioUrl = randomDoc.getString("audioUrl");

            if (audioUrl == null) {
                LOGGER.severe("Audio URL is missing in Firestore document: " + aid);
                return null;
            }

            return new Affirmation(aid, emotion, audioUrl);
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.log(Level.SEVERE, "Error fetching random affirmation for emotion: " + emotion, e);
            return null;
        }
    }
}
