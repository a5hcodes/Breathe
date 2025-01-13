package com.app.breathe.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "meditations")
public class Meditation {
    @Id
    private ObjectId tid;
    private String mood;
    private String title;
    private String audioFilePath; //url
    private String createdBy;
    private LocalDate localDate;


    public Meditation() {
        super();
    }
    public Meditation(String tid, String mood, String title, String audioFilePath, String createdBy, LocalDate localDate) {
        super();
    }
    public ObjectId getTid() {
        return tid;
    }
    public void setTid(ObjectId tid) {
        this.tid = tid;
    }

    public String getMood() {
        return mood;
    }
    public void setMood(String mood) {
        this.mood = mood;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAudioFilePath() {
        return audioFilePath;
    }
    public void setAudioFilePath(String audioFilePath) {
        this.audioFilePath = audioFilePath;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public LocalDate getLocalDate() {
        return localDate;
    }
    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }


}
