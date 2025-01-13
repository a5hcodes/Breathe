package com.app.breathe.repositories;

import com.app.breathe.entities.Meditation;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MeditationRepository extends MongoRepository<Meditation, ObjectId>{
    List<Meditation> findByMood(String mood);
//    List<Meditation> getAllMeditations(String title, String audioFilePath);
}
