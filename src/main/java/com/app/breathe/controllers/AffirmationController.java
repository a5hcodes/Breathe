package com.app.breathe.controllers;

import com.app.breathe.entities.Affirmation;
import com.app.breathe.service.AffirmationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/affirmations")
@CrossOrigin(origins = "*")
public class AffirmationController {

    private final AffirmationService affirmationService;

    public AffirmationController(AffirmationService affirmationService) {
        this.affirmationService = affirmationService;
    }

    // Fetch a random affirmation based on emotion
    @GetMapping("/{emotion}")
    public ResponseEntity<?> getRandomAffirmation(@PathVariable String emotion) {
        try {
            Affirmation affirmation = affirmationService.getRandomAffirmationByEmotion(emotion);
            if (affirmation != null) {
                return ResponseEntity.ok(affirmation);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No affirmation found for emotion: " + emotion);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching affirmation: " + e.getMessage());
        }
    }
}