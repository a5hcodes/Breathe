package com.app.breathe.service;

import com.app.breathe.entities.Mood;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class MoodService {

    @Autowired
    private Firestore firestore;

    //  Ensure UID Exists Before Saving Mood
    public void saveMood(Mood mood) throws ExecutionException, InterruptedException {
        DocumentSnapshot userDoc = firestore.collection("users").document(mood.getUid()).get().get();

        if (!userDoc.exists()) {
            throw new IllegalArgumentException("User with UID " + mood.getUid() + " does not exist.");
        }

        firestore.collection("moods")
                .document(mood.getUid() + "_" + mood.getDate())
                .set(mood)
                .get();
    }

    //  Fetch moods for the last 7 days for a given UID
    public List<Mood> getWeeklyMoods(String uid) throws ExecutionException, InterruptedException {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(7);

        QuerySnapshot snapshot = firestore.collection("moods")
                .whereEqualTo("uid", uid)
                .whereGreaterThanOrEqualTo("date", startDate.toString())
                .whereLessThanOrEqualTo("date", endDate.toString())
                .get()
                .get();

        return snapshot.getDocuments().stream()
                .map(doc -> doc.toObject(Mood.class))
                .collect(Collectors.toList());
    }
}
