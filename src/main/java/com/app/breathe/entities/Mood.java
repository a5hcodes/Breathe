package com.app.breathe.entities;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.PropertyName;
import com.google.cloud.Timestamp;


public class Mood {
    @DocumentId
    private String id;
    private String uid; // Ref to User
    private String mood;
    @PropertyName("date")
    private Timestamp date; // Changed from Date to Timestamp

    public Mood() {}

    public Mood(String id, String uid, Timestamp date, String mood) {
        this.id = id;
        this.uid = uid;
        this.date = date;
        this.mood = mood;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public Timestamp getDate() {
        return date;
    }
    public void setDate(Timestamp date) {
        this.date = date;
    }
    public String getMood() {
        return mood;
    }
    public void setMood(String mood) {
        this.mood = mood;
    }
}
