package com.uni.thesissystem.serviceImpl;

import com.uni.thesissystem.dto.ThesisDTO;
import com.uni.thesissystem.model.Thesis;
import com.uni.thesissystem.model.ThesisRequest;
import com.uni.thesissystem.repository.ThesisRepository;
import com.uni.thesissystem.repository.ThesisRequestRepository;
import com.uni.thesissystem.service.ThesisService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThesisServiceImpl implements ThesisService {

    @Autowired
    private ThesisRepository thesisRepository;

    @Autowired
    private ThesisRequestRepository thesisRequestRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ThesisDTO saveThesis(ThesisDTO thesisDTO) {
        Thesis thesis = modelMapper.map(thesisDTO, Thesis.class);

        ThesisRequest request = thesisRequestRepository.findById(thesisDTO.getRequestId())
                .orElseThrow(() -> new EntityNotFoundException("Thesis request not found"));
        thesis.setRequest(request);

        thesis.setSubmissionDate(thesisDTO.getSubmissionDate());
        thesis.setTitle(thesisDTO.getTitle());

        Thesis savedThesis = thesisRepository.save(thesis);
        return modelMapper.map(savedThesis, ThesisDTO.class);
    }

    @Override
    public ThesisDTO getThesisById(Long id) {
        Thesis thesis = thesisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Thesis not found"));
        return modelMapper.map(thesis, ThesisDTO.class);
    }

    @Override
    public List<ThesisDTO> getAllTheses() {
        return thesisRepository.findAll().stream()
                .map(thesis -> modelMapper.map(thesis, ThesisDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ThesisDTO updateThesis(Long id, ThesisDTO thesisDTO) {
        Thesis thesis = thesisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Thesis not found"));

        thesis.setSubmissionDate(thesisDTO.getSubmissionDate());
        thesis.setTitle(thesisDTO.getTitle());

        ThesisRequest request = thesisRequestRepository.findById(thesisDTO.getRequestId())
                .orElseThrow(() -> new EntityNotFoundException("Thesis request not found"));
        thesis.setRequest(request);

        Thesis updatedThesis = thesisRepository.save(thesis);
        return modelMapper.map(updatedThesis, ThesisDTO.class);
    }

    @Override
    public void deleteThesis(Long id) {
        thesisRepository.deleteById(id);
    }
}
