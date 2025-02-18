package com.app.breathe.entities;

import com.google.cloud.firestore.annotation.DocumentId;

public class Affirmation {
    @DocumentId
    private String aid;
    private String emotion;
    private String audioUrl;

    // Default constructor (required for Firestore)
    public Affirmation() {}

    public Affirmation(String aid, String emotion, String audioUrl) {
        this.aid = aid;
        this.emotion = emotion;
        this.audioUrl = audioUrl;
    }

    public String getAid() { return aid; }
    public void setAid(String aid) { this.aid = aid; }

    public String getEmotion() { return emotion; }
    public void setEmotion(String emotion) { this.emotion = emotion; }

    public String getAudioUrl() { return audioUrl; }
    public void setAudioUrl(String audioUrl) { this.audioUrl = audioUrl; }
}
