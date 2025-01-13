package com.app.breathe.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "moods")
public class Mood {

    @Id
    private ObjectId mid;
    private String uid; // Ref to User
    private LocalDate date;
    private String mood;

    public Mood() {
        super();
    }

    public Mood(ObjectId mid, String uid, LocalDate date, String mood) {
        this.mid = mid;
        this.uid = uid;
        this.date = date;
        this.mood = mood;
    }

    public ObjectId getMid() {
        return mid;
    }

    public void setMid(ObjectId mid) {
        this.mid = mid;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getMood() {
        return mood;
    }
    public void setMood(String mood) {
        this.mood = mood;
    }


}
