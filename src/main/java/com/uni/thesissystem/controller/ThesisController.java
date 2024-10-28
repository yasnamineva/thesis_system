package com.uni.thesissystem.controller;

import com.uni.thesissystem.dto.ThesisDTO;
import com.uni.thesissystem.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/thesis")
public class ThesisController {

    @Autowired
    private ThesisService thesisService;

    @PostMapping
    public ResponseEntity<ThesisDTO> createThesis(@RequestBody ThesisDTO thesisDTO) {
        ThesisDTO createdThesis = thesisService.saveThesis(thesisDTO);
        return new ResponseEntity<>(createdThesis, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThesisDTO> getThesisById(@PathVariable Long id) {
        ThesisDTO thesisDTO = thesisService.getThesisById(id);
        return new ResponseEntity<>(thesisDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ThesisDTO>> getAllTheses() {
        List<ThesisDTO> theses = thesisService.getAllTheses();
        return new ResponseEntity<>(theses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThesisDTO> updateThesis(@PathVariable Long id, @RequestBody ThesisDTO thesisDTO) {
        ThesisDTO updatedThesis = thesisService.updateThesis(id, thesisDTO);
        return new ResponseEntity<>(updatedThesis, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThesis(@PathVariable Long id) {
        thesisService.deleteThesis(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
