package com.uni.thesissystem.service;

import com.uni.thesissystem.dto.ThesisDefenseDTO;
import java.util.List;

public interface ThesisDefenseService {
    ThesisDefenseDTO saveThesisDefense(ThesisDefenseDTO thesisDefenseDTO);
    ThesisDefenseDTO getThesisDefenseById(Long id);
    ThesisDefenseDTO getThesisDefenseByThesisId(Long id);
    List<ThesisDefenseDTO> getAllThesisDefenses();
    ThesisDefenseDTO updateThesisDefense(Long id, ThesisDefenseDTO thesisDefenseDTO);
    void deleteThesisDefense(Long id);
}
