package com.uni.thesissystem.serviceImpl;

import com.uni.thesissystem.dto.ThesisRequestDTO;
import com.uni.thesissystem.exceptions.StudentHasThesisRequestException;
import com.uni.thesissystem.model.Student;
import com.uni.thesissystem.model.Teacher;
import com.uni.thesissystem.model.ThesisRequest;
import com.uni.thesissystem.repository.StudentRepository;
import com.uni.thesissystem.repository.TeacherRepository;
import com.uni.thesissystem.repository.ThesisRequestRepository;
import com.uni.thesissystem.service.ThesisRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThesisRequestServiceImpl implements ThesisRequestService {

    @Autowired
    private ThesisRequestRepository thesisRequestRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ThesisRequestDTO saveThesisRequest(ThesisRequestDTO thesisRequestDTO) {
        ThesisRequest thesisRequest = modelMapper.map(thesisRequestDTO, ThesisRequest.class);
        boolean studentHasRequest = thesisRequestRepository.existsByStudentId(thesisRequestDTO.getStudentId());
        if (studentHasRequest) {
            throw new StudentHasThesisRequestException("Student already has submitted a thesis request");
        }

        // Set the associated student and teacher
        Student student = studentRepository.findById(thesisRequestDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        thesisRequest.setStudent(student);

        Teacher supervisor = teacherRepository.findById(thesisRequestDTO.getSupervisorId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        thesisRequest.setSupervisor(supervisor);

        ThesisRequest savedRequest = thesisRequestRepository.save(thesisRequest);
        return modelMapper.map(savedRequest, ThesisRequestDTO.class);
    }

    @Override
    public ThesisRequestDTO getThesisRequestById(Long id) {
        ThesisRequest thesisRequest = thesisRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ThesisRequest not found"));
        return modelMapper.map(thesisRequest, ThesisRequestDTO.class);
    }

    @Override
    public List<ThesisRequestDTO> getAllThesisRequests() {
        return thesisRequestRepository.findAll().stream()
                .map(request -> modelMapper.map(request, ThesisRequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ThesisRequestDTO updateThesisRequest(Long id, ThesisRequestDTO thesisRequestDTO) {
        ThesisRequest thesisRequest = thesisRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ThesisRequest not found"));

        Long oldStudentId = thesisRequest.getStudent().getId();
        Long newStudentId = thesisRequestDTO.getStudentId();
        // Check if the student ID is changing
        if (!oldStudentId.equals(newStudentId)) {
            // Check if the new student already has a thesis request
            boolean studentHasRequest = thesisRequestRepository.existsByStudentId(newStudentId);
            if (studentHasRequest) {
                throw new StudentHasThesisRequestException("Student already has submitted a thesis request");
            }
        }

        thesisRequest.setTitle(thesisRequestDTO.getTitle());
        thesisRequest.setDescription(thesisRequestDTO.getDescription());

        Student student = studentRepository.findById(thesisRequestDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        thesisRequest.setStudent(student);

        Teacher supervisor = teacherRepository.findById(thesisRequestDTO.getSupervisorId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        thesisRequest.setSupervisor(supervisor);

        ThesisRequest updatedRequest = thesisRequestRepository.save(thesisRequest);
        return modelMapper.map(updatedRequest, ThesisRequestDTO.class);
    }

    @Override
    public void deleteThesisRequest(Long id) {
        thesisRequestRepository.deleteById(id);
    }
}
