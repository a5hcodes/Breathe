package com.app.breathe.repositories;

import com.app.breathe.entities.Mood;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MoodRepository extends MongoRepository<Mood, ObjectId> {
    @Query("{ 'uid': ?0, 'date': { $gte: ?1, $lte: ?2 } }")
    List<Mood> findMoodsByUidAndDateBetween(String uid, LocalDate startDate, LocalDate endDate);
    List<Mood> findByUid(String uid);
    List<Mood> findByUidAndDateAfter(String uid, LocalDate date);

}
