package com.uni.thesissystem.serviceImpl;

import com.uni.thesissystem.model.ThesisRequest;
import com.uni.thesissystem.repository.ThesisRequestRepository;
import com.uni.thesissystem.service.ThesisRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisRequestServiceImpl implements ThesisRequestService {

    @Autowired
    private ThesisRequestRepository thesisRequestRepository;

    @Override
    public List<ThesisRequest> getAllThesisRequests() {
        return thesisRequestRepository.findAll();
    }

    @Override
    public ThesisRequest getThesisRequestById(Long id) {
        return thesisRequestRepository.findById(id).orElse(null);
    }

    @Override
    public ThesisRequest createThesisRequest(ThesisRequest thesisRequest) {
        return thesisRequestRepository.save(thesisRequest);
    }

    @Override
    public ThesisRequest updateThesisRequest(Long id, ThesisRequest thesisRequest) {
        thesisRequest.setId(id);
        return thesisRequestRepository.save(thesisRequest);
    }

    @Override
    public void deleteThesisRequest(Long id) {
        thesisRequestRepository.deleteById(id);
    }
}
