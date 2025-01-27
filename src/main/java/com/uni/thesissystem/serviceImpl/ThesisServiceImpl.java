package com.uni.thesissystem.serviceImpl;

import com.uni.thesissystem.dto.ThesisDTO;
import com.uni.thesissystem.exceptions.EntityDeletionException;
import com.uni.thesissystem.model.Thesis;
import com.uni.thesissystem.model.ThesisRequest;
import com.uni.thesissystem.repository.ThesisRepository;
import com.uni.thesissystem.repository.ThesisRequestRepository;
import com.uni.thesissystem.service.ThesisService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    /**
     * Fetches the thesis associated with a thesis request.
     *
     * @param requestId The ID of the thesis request
     * @return ThesisDTO representing the associated thesis, or null if not found
     */
    @Override
    public ThesisDTO getThesisByRequestId(Long requestId) {
        Thesis thesis = thesisRepository.findByRequestId(requestId).orElse(null);
        return thesis != null ? modelMapper.map(thesis, ThesisDTO.class) : null;
    }

    @Override
    public void deleteThesis(Long id) {
        try{
            thesisRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityDeletionException("Thesis", id, ex.getMessage());
        }
    }
}
