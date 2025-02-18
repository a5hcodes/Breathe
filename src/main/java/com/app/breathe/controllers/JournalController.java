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
            return ResponseEntity.ok(journalService.addJournal(journal));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{uid}")
    public ResponseEntity<List<Journal>> getUserJournals(@PathVariable String uid) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(journalService.getAllJournalsByUser(uid));
    }

    @PutMapping("/update/{jid}")
    public ResponseEntity<?> updateJournal(@PathVariable String jid, @RequestBody Journal journal) {
        try {
            return ResponseEntity.ok(journalService.updateJournal(jid, journal.getTitle(), journal.getContent()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{jid}")
    public ResponseEntity<Boolean> deleteJournal(@PathVariable String jid) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(journalService.deleteJournal(jid));
    }
}
