package com.uni.thesissystem.controller;

import com.uni.thesissystem.dto.RecensionDTO;
import com.uni.thesissystem.service.RecensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recensions")
public class RecensionController {

    @Autowired
    private RecensionService recensionService;

    @PostMapping
    public ResponseEntity<RecensionDTO> createRecension(@RequestBody RecensionDTO recensionDTO) {
        RecensionDTO createdRecension = recensionService.saveRecension(recensionDTO);
        return new ResponseEntity<>(createdRecension, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecensionDTO> getRecensionById(@PathVariable Long id) {
        RecensionDTO recensionDTO = recensionService.getRecensionById(id);
        return new ResponseEntity<>(recensionDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RecensionDTO>> getAllRecensions() {
        List<RecensionDTO> recensions = recensionService.getAllRecensions();
        return new ResponseEntity<>(recensions, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecensionDTO> updateRecension(@PathVariable Long id, @RequestBody RecensionDTO recensionDTO) {
        RecensionDTO updatedRecension = recensionService.updateRecension(id, recensionDTO);
        return new ResponseEntity<>(updatedRecension, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecension(@PathVariable Long id) {
        recensionService.deleteRecension(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
