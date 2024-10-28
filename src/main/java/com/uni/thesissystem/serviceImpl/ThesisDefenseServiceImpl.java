package com.uni.thesissystem.serviceImpl;

import com.uni.thesissystem.dto.ThesisDefenseDTO;
import com.uni.thesissystem.model.Teacher;
import com.uni.thesissystem.model.Thesis;
import com.uni.thesissystem.model.ThesisDefense;
import com.uni.thesissystem.repository.TeacherRepository;
import com.uni.thesissystem.repository.ThesisDefenseRepository;
import com.uni.thesissystem.repository.ThesisRepository;
import com.uni.thesissystem.service.ThesisDefenseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThesisDefenseServiceImpl implements ThesisDefenseService {

    @Autowired
    private ThesisDefenseRepository thesisDefenseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ThesisRepository thesisRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ThesisDefenseDTO saveThesisDefense(ThesisDefenseDTO thesisDefenseDTO) {
        ThesisDefense thesisDefense = new ThesisDefense();
        thesisDefense.setMarks(thesisDefenseDTO.getMarks());
        thesisDefense.setDate(thesisDefenseDTO.getDate());

        Thesis thesis = thesisRepository.findById(thesisDefenseDTO.getThesisId())
                .orElseThrow(() -> new RuntimeException("Thesis not found"));
        thesisDefense.setThesis(thesis);

        List<Teacher> teachers = teacherRepository.findAllById(thesisDefenseDTO.getTeacherIds());
        thesisDefense.setTeachers(teachers);

        ThesisDefense savedThesisDefense = thesisDefenseRepository.save(thesisDefense);
        return modelMapper.map(savedThesisDefense, ThesisDefenseDTO.class);
    }

    @Override
    public ThesisDefenseDTO getThesisDefenseById(Long id) {
        ThesisDefense thesisDefense = thesisDefenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Thesis Defense not found"));
        return modelMapper.map(thesisDefense, ThesisDefenseDTO.class);
    }

    @Override
    public List<ThesisDefenseDTO> getAllThesisDefenses() {
        return thesisDefenseRepository.findAll().stream()
                .map(thesisDefense -> modelMapper.map(thesisDefense, ThesisDefenseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ThesisDefenseDTO updateThesisDefense(Long id, ThesisDefenseDTO thesisDefenseDTO) {
        ThesisDefense thesisDefense = thesisDefenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Thesis Defense not found"));

        thesisDefense.setMarks(thesisDefenseDTO.getMarks());
        thesisDefense.setDate(thesisDefenseDTO.getDate());

        Thesis thesis = thesisRepository.findById(thesisDefenseDTO.getThesisId())
                .orElseThrow(() -> new RuntimeException("Thesis not found"));
        thesisDefense.setThesis(thesis);

        List<Teacher> teachers = teacherRepository.findAllById(thesisDefenseDTO.getTeacherIds());
        thesisDefense.setTeachers(teachers);

        ThesisDefense updatedThesisDefense = thesisDefenseRepository.save(thesisDefense);
        return modelMapper.map(updatedThesisDefense, ThesisDefenseDTO.class);
    }

    @Override
    public void deleteThesisDefense(Long id) {
        thesisDefenseRepository.deleteById(id);
    }
}
