package com.app.breathe.controllers;

import com.app.breathe.entities.Journal;
import com.app.breathe.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/journals")
@CrossOrigin
public class JournalController {
    private final JournalService journalService;

    @Autowired
    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addJournal(@RequestBody Journal journal) {
        try {
            if (journal.getTitle() == null || journal.getTitle().trim().isEmpty() ||
                    journal.getContent() == null || journal.getContent().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Title and content cannot be empty.");
            }
            return ResponseEntity.ok(journalService.addJournal(journal));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{uid}")
    public ResponseEntity<?> getUserJournals(@PathVariable String uid) {
        try {
            List<Journal> journals = journalService.getAllJournalsByUser(uid);
            return ResponseEntity.ok(journals);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.internalServerError().body("Error fetching journals: " + e.getMessage());
        }
    }

    @PutMapping("/update/{jid}")
    public ResponseEntity<?> updateJournal(@PathVariable String jid, @RequestBody Journal journal) {
        try {
            return ResponseEntity.ok(journalService.updateJournal(jid, journal.getTitle(), journal.getContent()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating journal: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{jid}")
    public ResponseEntity<?> deleteJournal(@PathVariable String jid) {
        try {
            return ResponseEntity.ok(journalService.deleteJournal(jid) ? "Journal deleted successfully." : "Failed to delete journal.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error deleting journal: " + e.getMessage());
        }
    }
}
