package com.app.breathe.controllers;

import com.app.breathe.entities.Meditation;
import com.app.breathe.service.MeditationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/meditations")
public class MeditationController {

    @Autowired
    private MeditationService meditationService;

    // Get Random Meditation for a Mood (GET Request)
    @GetMapping("/random")
    public ResponseEntity<Meditation> getRandomMeditation(@RequestParam String mood) {
        try {
            Meditation meditation = meditationService.getRandomMeditation(mood);
            return ResponseEntity.ok(meditation);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
