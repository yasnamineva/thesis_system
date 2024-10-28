package com.uni.thesissystem.service;

import com.uni.thesissystem.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {
    TeacherDTO saveTeacher(TeacherDTO teacherDTO);
    TeacherDTO getTeacherById(Long id);
    List<TeacherDTO> getAllTeachers();
    TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO);
    void deleteTeacher(Long id);
}
