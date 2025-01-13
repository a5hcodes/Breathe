package com.app.breathe.controllers;

import com.app.breathe.entities.Meditation;
import com.app.breathe.service.MeditationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/meditations")
public class MeditationController {

    @Autowired
    private MeditationService meditationService;

    @PostMapping
    public Meditation addMeditation(@RequestBody Meditation meditation) {
        meditation.setLocalDate(LocalDate.now());
        meditationService.saveMeditation(meditation);
        return meditation;
    }

//    @GetMapping
//    public List<Meditation> getAllMeditation (@RequestParam String title, String audioFilePath){
//       return meditationService.getAllMeditation(title, audioFilePath);
//    }

    @GetMapping("/random")
    public ResponseEntity<?> getRandomMeditation(@RequestParam String mood) {
        try {
            Meditation meditation = meditationService.getRandomMeditation(mood);
            return ResponseEntity.ok(meditation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



}
