package com.app.breathe.entities;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.PropertyName;


import java.util.ArrayList;
import java.util.List;

public class Journal {

    @DocumentId
    private String jid;

    @PropertyName("uid")
    private String uid;

    @PropertyName("title")
    private String title;

    @PropertyName("content")
    private String content;

    @PropertyName("createdAt")
    private Timestamp createdAt;

    @PropertyName("updatedAt")
    private Timestamp updatedAt;

    @PropertyName("isDeleted")
    private boolean isDeleted;

    @PropertyName("versions")
    private List<JournalVersion> versions;

    public Journal() {
        this.versions = new ArrayList<>();
    }

    public Journal(String jid, String uid, String title, String content,
                   Timestamp createdAt, Timestamp updatedAt, boolean isDeleted,
                   List<JournalVersion> versions) {
        this.jid = jid;
        this.uid = uid;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
        this.versions = versions != null && !versions.isEmpty() ? versions : null;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<JournalVersion> getVersions() {
        return versions == null || versions.isEmpty() ? null : versions;
    }

    public void setVersions(List<JournalVersion> versions) {
        this.versions = versions != null && !versions.isEmpty() ? versions : null;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "jid='" + jid + '\'' +
                ", uid='" + uid + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", isDeleted=" + isDeleted +
                ", versions=" + versions +
                '}';
    }
}
