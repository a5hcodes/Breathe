package com.app.breathe.entities;


import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.PropertyName;

import java.util.Date;

public class Mood {
    @DocumentId
    private String id;
    private String uid; // Ref to User
    private String mood;
    @PropertyName("date")
    private Date date;

    public Mood() {}

    public Mood(String id, String uid, Date date, String mood) {
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
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getMood() {
        return mood;
    }
    public void setMood(String mood) {
        this.mood = mood;
    }


}
