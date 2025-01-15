package com.uni.thesissystem.controller;

import com.uni.thesissystem.dto.TeacherDTO;
import com.uni.thesissystem.dto.ThesisDefenseDTO;
import com.uni.thesissystem.service.TeacherService;
import com.uni.thesissystem.service.ThesisDefenseService;
import com.uni.thesissystem.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Controller
@RequestMapping("/thesis-defenses")
public class ThesisDefenseController {

    @Autowired
    private ThesisDefenseService thesisDefenseService;

    @Autowired
    private ThesisService thesisService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/create")
    public String showCreateThesisDefenseForm(Model model) {
        model.addAttribute("thesisDefense", new ThesisDefenseDTO());
        model.addAttribute("teachers", teacherService.getAllTeachers());
        model.addAttribute("theses", thesisService.getAllTheses());
        return "thesis-defenses/create-thesis-defense";
    }

    @PostMapping("/create")
    public String createThesisDefense(@Valid @ModelAttribute("thesisDefense") ThesisDefenseDTO thesisDefenseDTO,
                                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("teachers", teacherService.getAllTeachers());
            model.addAttribute("theses", thesisService.getAllTheses());
            return "thesis-defenses/create-thesis-defense";
        }
        thesisDefenseService.saveThesisDefense(thesisDefenseDTO);
        return "redirect:/thesis-defenses";
    }

    @GetMapping("/{id}")
    public String getThesisDefenseById(@PathVariable Long id, Model model) {
        ThesisDefenseDTO thesisDefenseDTO = thesisDefenseService.getThesisDefenseById(id);

        TeacherDTO teacher = teacherService.getTeacherById(thesisDefenseDTO.getTeacherId());
        model.addAttribute("thesisDefense", thesisDefenseDTO);
        model.addAttribute("teacher", teacher);

        return "thesis-defenses/view-thesis-defense";
    }


    @GetMapping
    public String getAllThesisDefenses(Model model) {
        List<ThesisDefenseDTO> thesisDefenses = thesisDefenseService.getAllThesisDefenses();

        Map<Long, TeacherDTO> teacherMap = teacherService.getAllTeachers().stream()
                .collect(Collectors.toMap(TeacherDTO::getId, teacher -> teacher));

        model.addAttribute("thesisDefenses", thesisDefenses);
        model.addAttribute("teachers", teacherMap);
        return "thesis-defenses/list-thesis-defenses";
    }

    @GetMapping("/{id}/edit")
    public String showEditThesisDefenseForm(@PathVariable Long id, Model model) {
        ThesisDefenseDTO thesisDefenseDTO = thesisDefenseService.getThesisDefenseById(id);
        model.addAttribute("thesisDefense", thesisDefenseDTO);
        model.addAttribute("teachers", teacherService.getAllTeachers());
        model.addAttribute("theses", thesisService.getAllTheses());
        return "thesis-defenses/edit-thesis-defense";
    }

    @PostMapping("/{id}/edit")
    public String updateThesisDefense(@PathVariable Long id,
                                      @Valid @ModelAttribute("thesisDefense") ThesisDefenseDTO thesisDefenseDTO,
                                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("teachers", teacherService.getAllTeachers());
            model.addAttribute("theses", thesisService.getAllTheses());
            return "thesis-defenses/edit-thesis-defense";
        }
        thesisDefenseService.updateThesisDefense(id, thesisDefenseDTO);
        return "redirect:/thesis-defenses";
    }

    @PostMapping("/{id}/delete")
    public String deleteThesisDefense(@PathVariable Long id) {
        thesisDefenseService.deleteThesisDefense(id);
        return "redirect:/thesis-defenses";
    }
}
