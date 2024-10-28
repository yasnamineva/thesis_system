package com.uni.thesissystem.controller;

import com.uni.thesissystem.dto.ThesisDefenseDTO;
import com.uni.thesissystem.service.ThesisDefenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/thesis-defenses")
public class ThesisDefenseController {

    @Autowired
    private ThesisDefenseService thesisDefenseService;

    @PostMapping
    public ResponseEntity<ThesisDefenseDTO> createThesisDefense(@RequestBody ThesisDefenseDTO thesisDefenseDTO) {
        ThesisDefenseDTO createdThesisDefense = thesisDefenseService.saveThesisDefense(thesisDefenseDTO);
        return new ResponseEntity<>(createdThesisDefense, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThesisDefenseDTO> getThesisDefenseById(@PathVariable Long id) {
        ThesisDefenseDTO thesisDefenseDTO = thesisDefenseService.getThesisDefenseById(id);
        return new ResponseEntity<>(thesisDefenseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ThesisDefenseDTO>> getAllThesisDefenses() {
        List<ThesisDefenseDTO> thesisDefenses = thesisDefenseService.getAllThesisDefenses();
        return new ResponseEntity<>(thesisDefenses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThesisDefenseDTO> updateThesisDefense(@PathVariable Long id, @RequestBody ThesisDefenseDTO thesisDefenseDTO) {
        ThesisDefenseDTO updatedThesisDefense = thesisDefenseService.updateThesisDefense(id, thesisDefenseDTO);
        return new ResponseEntity<>(updatedThesisDefense, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThesisDefense(@PathVariable Long id) {
        thesisDefenseService.deleteThesisDefense(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
