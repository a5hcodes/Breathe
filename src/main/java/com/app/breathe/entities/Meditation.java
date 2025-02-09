package com.app.breathe.entities;


import com.google.cloud.firestore.annotation.DocumentId;

public class Meditation {
    @DocumentId
    private String id;

    private String mood;
    private String title;
    private String audioUrl;

    public Meditation(){}


    public Meditation(String id,String mood, String title, String audioUrl) {
        this.id = id;
        this.mood = mood;
        this.title = title;
        this.audioUrl = audioUrl;
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
    public String getAudioUrl() {
        return audioUrl;
    }
    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }


}
