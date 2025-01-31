package com.uni.thesissystem.serviceImpl;

import com.uni.thesissystem.dto.ThesisDefenseDTO;
import com.uni.thesissystem.exceptions.EntityDeletionException;
import com.uni.thesissystem.model.Teacher;
import com.uni.thesissystem.model.Thesis;
import com.uni.thesissystem.model.ThesisDefense;
import com.uni.thesissystem.repository.TeacherRepository;
import com.uni.thesissystem.repository.ThesisDefenseRepository;
import com.uni.thesissystem.repository.ThesisRepository;
import com.uni.thesissystem.service.ThesisDefenseService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public ThesisDefenseDTO saveThesisDefense(ThesisDefenseDTO thesisDefenseDTO) {
        ThesisDefense thesisDefense = new ThesisDefense();
        thesisDefense.setMarks(thesisDefenseDTO.getMarks());
        thesisDefense.setDate(thesisDefenseDTO.getDate());

        Thesis thesis = thesisRepository.findById(thesisDefenseDTO.getThesisId())
                .orElseThrow(() -> new EntityNotFoundException("Thesis not found"));
        thesisDefense.setThesis(thesis);

        Teacher teacher = teacherRepository.findById(thesisDefenseDTO.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found"));
        thesisDefense.setTeacher(teacher);

        // Add the defense to the teacher's list of defenses
        teacher.getDefenses().add(thesisDefense);

        // Save the thesis defense
        ThesisDefense savedThesisDefense = thesisDefenseRepository.save(thesisDefense);
        return modelMapper.map(savedThesisDefense, ThesisDefenseDTO.class);
    }


    @Override
    public ThesisDefenseDTO getThesisDefenseById(Long id) {
        ThesisDefense thesisDefense = thesisDefenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Thesis Defense not found"));
        return modelMapper.map(thesisDefense, ThesisDefenseDTO.class);
    }

    @Override
    public ThesisDefenseDTO getThesisDefenseByThesisId(Long id) {
        ThesisDefense thesisDefense = thesisDefenseRepository.findByThesisId(id);

        if (thesisDefense == null) {
            throw new EntityNotFoundException("Thesis Defense not found");
        }
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
                .orElseThrow(() -> new EntityNotFoundException("Thesis Defense not found"));

        thesisDefense.setMarks(thesisDefenseDTO.getMarks());
        thesisDefense.setDate(thesisDefenseDTO.getDate());

        Thesis thesis = thesisRepository.findById(thesisDefenseDTO.getThesisId())
                .orElseThrow(() -> new EntityNotFoundException("Thesis not found"));
        thesisDefense.setThesis(thesis);

        Teacher teacher = teacherRepository.findById(thesisDefenseDTO.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found"));
        thesisDefense.setTeacher(teacher);

        ThesisDefense updatedThesisDefense = thesisDefenseRepository.save(thesisDefense);
        return modelMapper.map(updatedThesisDefense, ThesisDefenseDTO.class);
    }

    @Override
    public void deleteThesisDefense(Long id) {
        try{
            thesisDefenseRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityDeletionException("Thesis defense", id, ex.getMessage());
        }
    }
}
