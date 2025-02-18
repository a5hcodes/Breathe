package com.app.breathe.service;

import com.app.breathe.entities.Journal;
import com.app.breathe.entities.JournalVersion;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class JournalService {
    private final Firestore firestore;

    @Autowired
    public JournalService(Firestore firestore) {
        this.firestore = firestore;
    }

    public List<Journal> getAllJournalsByUser(String uid) throws ExecutionException, InterruptedException {
        QuerySnapshot snapshot = firestore.collection("journals")
                .whereEqualTo("uid", uid)
                .whereEqualTo("isDeleted", false)
                .get()
                .get();

        return snapshot.getDocuments().stream()
                .map(doc -> doc.toObject(Journal.class))
                .collect(Collectors.toList());
    }

    public Journal addJournal(Journal journal) throws ExecutionException, InterruptedException {
        if (journal.getTitle().isEmpty() || journal.getContent().isEmpty()) {
            throw new IllegalArgumentException("Title and Content cannot be empty");
        }

        journal.setJid(UUID.randomUUID().toString());
        journal.setCreatedAt(Timestamp.now());
        journal.setUpdatedAt(Timestamp.now());
        journal.setDeleted(false);

        firestore.collection("journals").document(journal.getJid()).set(journal).get();
        return journal;
    }

    public boolean deleteJournal(String jid) throws ExecutionException, InterruptedException {
        DocumentReference journalRef = firestore.collection("journals").document(jid);
        journalRef.update("isDeleted", true).get();
        return true;
    }

    public Journal updateJournal(String jid, String newTitle, String newContent) throws ExecutionException, InterruptedException {
        DocumentReference journalRef = firestore.collection("journals").document(jid);
        DocumentSnapshot document = journalRef.get().get();

        if (!document.exists()) throw new IllegalArgumentException("Journal not found");

        Journal journal = document.toObject(Journal.class);

        // Add previous version before updating
        if (journal.getVersions() != null) {
            journal.getVersions().add(new JournalVersion(journal.getTitle(), journal.getContent(), journal.getUpdatedAt()));
        } else {
            journal.setVersions(List.of(new JournalVersion(journal.getTitle(), journal.getContent(), journal.getUpdatedAt())));
        }

        journal.setTitle(newTitle);
        journal.setContent(newContent);
        journal.setUpdatedAt(Timestamp.now());

        // Remove versions if empty
        if (journal.getVersions().isEmpty()) {
            journalRef.update("versions", FieldValue.delete()).get();
        } else {
            journalRef.set(journal).get();
        }

        return journal;
    }
}
