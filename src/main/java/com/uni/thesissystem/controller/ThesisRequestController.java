package com.uni.thesissystem.controller;

import com.uni.thesissystem.model.ThesisRequest;
import com.uni.thesissystem.service.ThesisRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/thesis-requests")
public class ThesisRequestController {

    @Autowired
    private ThesisRequestService thesisRequestService;

    @GetMapping
    public ResponseEntity<List<ThesisRequest>> getAllThesisRequests() {
        return ResponseEntity.ok(thesisRequestService.getAllThesisRequests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThesisRequest> getThesisRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(thesisRequestService.getThesisRequestById(id));
    }

    @PostMapping
    public ResponseEntity<ThesisRequest> createThesisRequest(@RequestBody ThesisRequest thesisRequest) {
        return ResponseEntity.ok(thesisRequestService.createThesisRequest(thesisRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThesisRequest> updateThesisRequest(@PathVariable Long id, @RequestBody ThesisRequest thesisRequest) {
        return ResponseEntity.ok(thesisRequestService.updateThesisRequest(id, thesisRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThesisRequest(@PathVariable Long id) {
        thesisRequestService.deleteThesisRequest(id);
        return ResponseEntity.noContent().build();
    }
}
