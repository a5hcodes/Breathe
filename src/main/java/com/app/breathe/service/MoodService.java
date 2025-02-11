package com.app.breathe.service;

import com.app.breathe.entities.Mood;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class MoodService {

    @Autowired
    private Firestore firestore;

    //  Ensure UID Exists Before Saving Mood
    public void saveMood(Mood mood) throws ExecutionException, InterruptedException {
        if (mood.getUid() == null || mood.getMood() == null) {
            throw new IllegalArgumentException("User ID and Mood cannot be null.");
        }

        // Convert current date to Firestore Timestamp
        mood.setDate(Timestamp.now());

        // Ensure the user exists
        DocumentReference userRef = firestore.collection("users").document(mood.getUid());
        if (!userRef.get().get().exists()) {
            throw new IllegalArgumentException("User with UID " + mood.getUid() + " does not exist.");
        }

        // Generate unique ID
        DocumentReference moodDoc = firestore.collection("moods").document();
        mood.setId(moodDoc.getId());

        moodDoc.set(mood).get();
    }

    // Fetch moods for the last 7 days for a given UID
    public List<Mood> getWeeklyMoods(String uid) throws ExecutionException, InterruptedException {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(7);

        // Convert LocalDate to Firestore Timestamp
        Timestamp startTimestamp = Timestamp.of(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        Timestamp endTimestamp = Timestamp.of(Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        QuerySnapshot snapshot = firestore.collection("moods")
                .whereEqualTo("uid", uid)
                .whereGreaterThanOrEqualTo("date", startTimestamp)
                .whereLessThanOrEqualTo("date", endTimestamp)
                .get()
                .get();

        return snapshot.getDocuments().stream()
                .map(doc -> doc.toObject(Mood.class))
                .collect(Collectors.toList());
    }
}
