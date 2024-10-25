package com.uni.thesissystem.serviceImpl;

import com.uni.thesissystem.model.ThesisDefense;
import com.uni.thesissystem.repository.ThesisDefenseRepository;
import com.uni.thesissystem.service.ThesisDefenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisDefenseServiceImpl implements ThesisDefenseService {

    @Autowired
    private ThesisDefenseRepository thesisDefenseRepository;

    @Override
    public List<ThesisDefense> getAllThesisDefenses() {
        return thesisDefenseRepository.findAll();
    }

    @Override
    public ThesisDefense getThesisDefenseById(Long id) {
        return thesisDefenseRepository.findById(id).orElse(null);
    }

    @Override
    public ThesisDefense createThesisDefense(ThesisDefense thesisDefense) {
        return thesisDefenseRepository.save(thesisDefense);
    }

    @Override
    public ThesisDefense updateThesisDefense(Long id, ThesisDefense thesisDefense) {
        thesisDefense.setId(id);
        return thesisDefenseRepository.save(thesisDefense);
    }

    @Override
    public void deleteThesisDefense(Long id) {
        thesisDefenseRepository.deleteById(id);
    }
}
