package com.app.breathe.service;

import com.app.breathe.entities.Meditation;
import com.app.breathe.repositories.MeditationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MeditationService {

    @Autowired
    private MeditationRepository meditationRepository;

    public Meditation saveMeditation(Meditation meditation) {
        return meditationRepository.save(meditation);
    }

//    public List<Meditation> getAllMeditation(String title, String audioFilePath) {
//        return meditationRepository.getAllMeditations(title, audioFilePath);
//    }

    public Meditation getRandomMeditation(String mood) {
        List<Meditation> meditationList = meditationRepository.findByMood(mood);

        if (meditationList.isEmpty()) {
            throw new IllegalArgumentException("No meditations found for the mood: " + mood);
        }
        Random random = new Random();
        return meditationList.get(random.nextInt(meditationList.size()));
    }
    }
