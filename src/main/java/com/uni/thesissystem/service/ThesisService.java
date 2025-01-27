package com.uni.thesissystem.service;

import com.uni.thesissystem.dto.ThesisDTO;
import com.uni.thesissystem.dto.ThesisRequestDTO;

import java.util.List;

public interface ThesisService {
    ThesisDTO saveThesis(ThesisDTO thesisDTO);
    ThesisDTO getThesisById(Long id);
    List<ThesisDTO> getAllTheses();
    ThesisDTO updateThesis(Long id, ThesisDTO thesisDTO);
    ThesisDTO getThesisByRequestId(Long requestId);
    void deleteThesis(Long id);
}
