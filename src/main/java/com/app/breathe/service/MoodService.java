package com.app.breathe.service;

import com.app.breathe.entities.Mood;
import com.app.breathe.repositories.MoodRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MoodService {

    @Autowired
    private MoodRepository moodRepository;

    public Mood saveMood(Mood mood) {
        return moodRepository.save(mood);
    }

    public List<Mood> getAllMoods(String uid) {
        return moodRepository.findByUid(uid);
    }

    public Mood getMoodById(ObjectId mid) {
        return moodRepository.findById(mid).orElseThrow(() -> new RuntimeException("Mood not found"));
    }

    public Mood updateMood(ObjectId mid, String uid , String mood ) {
        Mood m = getMoodById(mid);
        m.setMood(mood);
        m.setUid(uid);
        return moodRepository.save(m);
    }

    public void deleteMoodById(ObjectId mid){
         moodRepository.deleteById(mid);
    }

    public List<Mood> getWeeklyMoods (String uid) {
        LocalDate endDate =  LocalDate.now();
        LocalDate startDate = endDate.minusDays(7);
        return moodRepository.findMoodsByUidAndDateBetween(uid, startDate,endDate);

    }




}
