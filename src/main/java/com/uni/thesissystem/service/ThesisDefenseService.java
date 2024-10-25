package com.uni.thesissystem.service;

import com.uni.thesissystem.model.ThesisDefense;

import java.util.List;

public interface ThesisDefenseService {
    List<ThesisDefense> getAllThesisDefenses();
    ThesisDefense getThesisDefenseById(Long id);
    ThesisDefense createThesisDefense(ThesisDefense thesisDefense);
    ThesisDefense updateThesisDefense(Long id, ThesisDefense thesisDefense);
    void deleteThesisDefense(Long id);
}
