package com.uni.thesissystem.controller;

import com.uni.thesissystem.dto.TeacherDTO;
import com.uni.thesissystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/create")
    public String showCreateTeacherForm(Model model) {
        model.addAttribute("teacher", new TeacherDTO());
        return "teachers/create-teacher";
    }

    @PostMapping("/create")
    public String createTeacher(@Valid @ModelAttribute("teacher") TeacherDTO teacherDTO,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "teachers/create-teacher";
        }
        teacherService.saveTeacher(teacherDTO);
        return "redirect:/teachers";
    }

    @GetMapping("/{id}")
    public String getTeacherById(@PathVariable Long id, Model model) {
        TeacherDTO teacherDTO = teacherService.getTeacherById(id);
        model.addAttribute("teacher", teacherDTO);
        return "teachers/view-teacher";
    }

    @GetMapping
    public String getAllTeachers(Model model) {
        List<TeacherDTO> teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "teachers/list-teachers";
    }

    @GetMapping("/{id}/edit")
    public String showEditTeacherForm(@PathVariable Long id, Model model) {
        TeacherDTO teacherDTO = teacherService.getTeacherById(id);
        model.addAttribute("teacher", teacherDTO);
        return "teachers/edit-teacher";
    }

    @PostMapping("/{id}/edit")
    public String updateTeacher(@PathVariable Long id,
                                @Valid @ModelAttribute("teacher") TeacherDTO teacherDTO,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "teachers/edit-teacher";
        }
        teacherService.updateTeacher(id, teacherDTO);
        return "redirect:/teachers";
    }

    @PostMapping("/{id}/delete")
    public String deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return "redirect:/teachers";
    }
}
