package com.uni.thesissystem.controller;

import com.uni.thesissystem.dto.StudentDTO;
import com.uni.thesissystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/create")
    public String showCreateStudentForm(Model model) {
        model.addAttribute("student", new StudentDTO());
        return "students/create-student";
    }

    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute("student") StudentDTO studentDTO,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "students/create-student";
        }
        studentService.saveStudent(studentDTO);
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String getStudentById(@PathVariable Long id, Model model) {
        StudentDTO studentDTO = studentService.getStudentById(id);
        model.addAttribute("student", studentDTO);
        return "students/view-student";
    }

    @GetMapping
    public String getAllStudents(Model model) {
        List<StudentDTO> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students/list-students"; // Return the list of students
    }

    @GetMapping("/{id}/edit")
    public String showEditStudentForm(@PathVariable Long id, Model model) {
        StudentDTO studentDTO = studentService.getStudentById(id);
        model.addAttribute("student", studentDTO);
        return "students/edit-student"; // Return the edit form
    }

    @PostMapping("/{id}/edit")
    public String updateStudent(@PathVariable Long id,
                                @Valid @ModelAttribute("student") StudentDTO studentDTO,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "students/edit-student";
        }
        studentService.updateStudent(id, studentDTO);
        return "redirect:/students";
    }

    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
