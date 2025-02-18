package com.app.breathe.controllers;

import com.app.breathe.entities.Mood;
import com.app.breathe.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/moods")
public class MoodController {

    @Autowired
    private MoodService moodService;

    //  Save Mood (Only if UID matches authenticated user)
    @PostMapping
    public ResponseEntity<String> createMood(@RequestBody Mood mood, Authentication authentication) {
        try {
            String loggedInUid = authentication.getName(); // Extract UID from JWT token

            // Prevent saving mood for another user
            if (mood.getUid() == null || !mood.getUid().equals(loggedInUid)) {
                return ResponseEntity.status(403).body("Unauthorized: Cannot save mood for another user.");
            }

            // Call service to save the mood
            moodService.saveMood(mood);
            return ResponseEntity.ok("Mood saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error saving mood: " + e.getMessage());
        }
    }

    //  Get Weekly Mood Data (Only for Authenticated User)
    @GetMapping("/weekly")
    public ResponseEntity<?> getWeeklyMoods(Authentication authentication) {
        try {
            String uid = authentication.getName(); // Ensure user gets only their moods
            List<Mood> moods = moodService.getWeeklyMoods(uid);

            if (moods.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(moods);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching moods: " + e.getMessage());
        }
    }
}
