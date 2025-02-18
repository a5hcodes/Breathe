package com.app.breathe.service;

import com.app.breathe.entities.Mood;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Query;
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

    private Firestore firestore;

    public MoodService(Firestore firestore) {
        this.firestore = firestore;
    }

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
        if (uid == null || uid.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty.");
        }

        // Get timestamps for last 7 days
        Instant now = Instant.now();
        Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant sevenDaysAgo = startOfToday.minusSeconds(7 * 24 * 60 * 60); // Go back exactly 7 days

        Timestamp startTimestamp = Timestamp.of(Date.from(sevenDaysAgo));
        Timestamp endTimestamp = Timestamp.of(Date.from(now)); // Use 'now' to capture all times today

        // Fetch moods from Firestore
        QuerySnapshot snapshot = firestore.collection("moods")
                .whereEqualTo("uid", uid) // Ensure only fetching for logged-in user
                .orderBy("date", Query.Direction.DESCENDING) // Firestore requires ordering before range filtering
                .whereGreaterThanOrEqualTo("date", startTimestamp)
                .whereLessThanOrEqualTo("date", endTimestamp)
                .get()
                .get();

        return snapshot.getDocuments().stream()
                .map(doc -> doc.toObject(Mood.class))
                .collect(Collectors.toList());
    }

}
