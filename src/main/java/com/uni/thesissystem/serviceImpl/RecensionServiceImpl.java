package com.uni.thesissystem.serviceImpl;

import com.uni.thesissystem.dto.RecensionDTO;
import com.uni.thesissystem.model.Recension;
import com.uni.thesissystem.model.Thesis;
import com.uni.thesissystem.repository.RecensionRepository;
import com.uni.thesissystem.repository.ThesisRepository;
import com.uni.thesissystem.service.RecensionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecensionServiceImpl implements RecensionService {

    @Autowired
    private RecensionRepository recensionRepository;

    @Autowired
    private ThesisRepository thesisRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RecensionDTO saveRecension(RecensionDTO recensionDTO) {
        Recension recension = modelMapper.map(recensionDTO, Recension.class);

        Thesis thesis = thesisRepository.findById(recensionDTO.getThesisId())
                .orElseThrow(() -> new RuntimeException("Thesis not found"));
        recension.setThesis(thesis);

        recension.setSubmissionDate(recensionDTO.getSubmissionDate());
        recension.setConclusion(recensionDTO.getConclusion());

        Recension savedRecension = recensionRepository.save(recension);
        return modelMapper.map(savedRecension, RecensionDTO.class);
    }

    @Override
    public RecensionDTO getRecensionById(Long id) {
        Recension recension = recensionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recension not found"));
        return modelMapper.map(recension, RecensionDTO.class);
    }

    @Override
    public List<RecensionDTO> getAllRecensions() {
        return recensionRepository.findAll().stream()
                .map(recension -> modelMapper.map(recension, RecensionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RecensionDTO updateRecension(Long id, RecensionDTO recensionDTO) {
        Recension recension = recensionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recension not found"));

        recension.setSubmissionDate(recensionDTO.getSubmissionDate());
        recension.setConclusion(recensionDTO.getConclusion());

        Thesis thesis = thesisRepository.findById(recensionDTO.getThesisId())
                .orElseThrow(() -> new RuntimeException("Thesis not found"));
        recension.setThesis(thesis);

        Recension updatedRecension = recensionRepository.save(recension);
        return modelMapper.map(updatedRecension, RecensionDTO.class);
    }

    @Override
    public void deleteRecension(Long id) {
        recensionRepository.deleteById(id);
    }
}
