package com.app.breathe.entities;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.PropertyName;

import java.util.ArrayList;
import java.util.List;

public class Journal {

    private String jid;
    private String uid;
    private String title;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private boolean deleted;
    private List<JournalVersion> versions;

    public Journal() {
        this.versions = new ArrayList<>();
    }

    public Journal(String jid, String uid, String title, String content, Timestamp createdAt, Timestamp updatedAt, boolean deleted, List<JournalVersion> versions) {
        this.jid = jid;
        this.uid = uid;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
        this.versions = (versions != null) ? versions : new ArrayList<>();
    }

    @PropertyName("jid")
    public String getJid() { return jid; }
    @PropertyName("jid")
    public void setJid(String jid) { this.jid = jid; }

    @PropertyName("uid")
    public String getUid() { return uid; }
    @PropertyName("uid")
    public void setUid(String uid) { this.uid = uid; }

    @PropertyName("title")
    public String getTitle() { return title; }
    @PropertyName("title")
    public void setTitle(String title) { this.title = title; }

    @PropertyName("content")
    public String getContent() { return content; }
    @PropertyName("content")
    public void setContent(String content) { this.content = content; }

    @PropertyName("createdAt")
    public Timestamp getCreatedAt() { return createdAt; }
    @PropertyName("createdAt")
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @PropertyName("updatedAt")
    public Timestamp getUpdatedAt() { return updatedAt; }
    @PropertyName("updatedAt")
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    @PropertyName("deleted")
    public boolean isDeleted() { return deleted; }
    @PropertyName("deleted")
    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    @PropertyName("versions")
    public List<JournalVersion> getVersions() { return versions; }
    @PropertyName("versions")
    public void setVersions(List<JournalVersion> versions) { this.versions = versions; }
}
