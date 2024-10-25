package com.uni.thesissystem.service;

import com.uni.thesissystem.model.ThesisRequest;

import java.util.List;

public interface ThesisRequestService {
    List<ThesisRequest> getAllThesisRequests();
    ThesisRequest getThesisRequestById(Long id);
    ThesisRequest createThesisRequest(ThesisRequest thesisRequest);
    ThesisRequest updateThesisRequest(Long id, ThesisRequest thesisRequest);
    void deleteThesisRequest(Long id);
}
