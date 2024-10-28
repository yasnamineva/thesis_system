package com.uni.thesissystem.controller;

import com.uni.thesissystem.dto.ThesisRequestDTO;
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

    @PostMapping
    public ResponseEntity<ThesisRequestDTO> createThesisRequest(@RequestBody ThesisRequestDTO thesisRequestDTO) {
        ThesisRequestDTO savedRequest = thesisRequestService.saveThesisRequest(thesisRequestDTO);
        return ResponseEntity.ok(savedRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThesisRequestDTO> getThesisRequestById(@PathVariable Long id) {
        ThesisRequestDTO thesisRequestDTO = thesisRequestService.getThesisRequestById(id);
        return ResponseEntity.ok(thesisRequestDTO);
    }

    @GetMapping
    public ResponseEntity<List<ThesisRequestDTO>> getAllThesisRequests() {
        List<ThesisRequestDTO> thesisRequests = thesisRequestService.getAllThesisRequests();
        return ResponseEntity.ok(thesisRequests);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThesisRequestDTO> updateThesisRequest(@PathVariable Long id, @RequestBody ThesisRequestDTO thesisRequestDTO) {
        ThesisRequestDTO updatedRequest = thesisRequestService.updateThesisRequest(id, thesisRequestDTO);
        return ResponseEntity.ok(updatedRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThesisRequest(@PathVariable Long id) {
        thesisRequestService.deleteThesisRequest(id);
        return ResponseEntity.noContent().build();
    }
}
