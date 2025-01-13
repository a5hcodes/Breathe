package com.app.breathe.controllers;

import com.app.breathe.entities.Meditation;
import com.app.breathe.entities.Mood;
import com.app.breathe.service.MeditationService;
import com.app.breathe.service.MoodService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/moods")
public class MoodController {

    @Autowired
    private MoodService moodService;
//    private MeditationService meditationService;

    @GetMapping
    public List <Mood> getAllMoods(@RequestParam String uid){
        return moodService.getAllMoods(uid);
    }

    @PostMapping
    public Mood createMood(@RequestBody Mood mood){
        mood.setDate(LocalDate.now());
        moodService.saveMood(mood);
        return mood;
    }

//    public Mood getMood(){
//        return new Mood( "user1", LocalDate.now(), "happy");
//    }

    @GetMapping("/{id}")
    public Mood getMoodById(@PathVariable ObjectId id){
        return moodService.getMoodById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteMoodById(@PathVariable ObjectId id){
        moodService.deleteMoodById(id);
        return "Mood with ID " + id + "was deleted.";
    }

    @PutMapping("/{id}")
    public Mood updateMoodById(@PathVariable ObjectId id, @RequestBody Mood mood){
        return moodService.updateMood(id,mood.getUid(), mood.getMood());
    }


    @GetMapping("/weekly")
    public ResponseEntity<List<Mood>> getWeeklyMoods(@RequestParam String uid){
        List <Mood> moods =  moodService.getWeeklyMoods(uid);
        if (moods.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(moods);
        }
    }

//
//    @GetMapping("/meditation/{mood}")
//    public Meditation getRandomMeditation(@PathVariable String mood){
//        return meditationService.getRandomMeditation(mood);
//    }
}

