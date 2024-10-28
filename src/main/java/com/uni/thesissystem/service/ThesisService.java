package com.uni.thesissystem.service;

import com.uni.thesissystem.dto.ThesisDTO;
import java.util.List;

public interface ThesisService {
    ThesisDTO saveThesis(ThesisDTO thesisDTO);
    ThesisDTO getThesisById(Long id);
    List<ThesisDTO> getAllTheses();
    ThesisDTO updateThesis(Long id, ThesisDTO thesisDTO);
    void deleteThesis(Long id);
}
