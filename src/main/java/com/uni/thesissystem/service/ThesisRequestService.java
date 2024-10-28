package com.uni.thesissystem.service;

import com.uni.thesissystem.dto.ThesisRequestDTO;

import java.util.List;

public interface ThesisRequestService {
    ThesisRequestDTO saveThesisRequest(ThesisRequestDTO thesisRequestDTO);
    ThesisRequestDTO getThesisRequestById(Long id);
    List<ThesisRequestDTO> getAllThesisRequests();
    ThesisRequestDTO updateThesisRequest(Long id, ThesisRequestDTO thesisRequestDTO);
    void deleteThesisRequest(Long id);
}
