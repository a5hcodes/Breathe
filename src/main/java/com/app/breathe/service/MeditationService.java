package com.app.breathe.service;

import com.app.breathe.entities.Meditation;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class MeditationService {

    private final Firestore firestore; // Declare Firestore as a final dependency

    @Autowired
    public MeditationService(Firestore firestore) {
        this.firestore = firestore;
    }

    // 🔹 Fetch a Random Meditation Based on Mood
    public Meditation getRandomMeditation(String mood) throws ExecutionException, InterruptedException {
        QuerySnapshot snapshot = firestore.collection("meditations")
                .whereEqualTo("mood", mood)
                .get()
                .get();

        List<Meditation> meditations = snapshot.getDocuments().stream()
                .map(doc -> doc.toObject(Meditation.class))
                .collect(Collectors.toList());

        if (meditations.isEmpty()) {
            throw new IllegalArgumentException("No meditations found for mood: " + mood);
        }

        return meditations.get(new Random().nextInt(meditations.size()));
    }
}
