package com.uni.thesissystem.controller;

import com.uni.thesissystem.dto.ThesisDTO;
import com.uni.thesissystem.dto.ThesisRequestDTO;
import com.uni.thesissystem.service.ThesisRequestService;
import com.uni.thesissystem.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import java.util.List;

@Controller
@RequestMapping("/theses")
public class ThesisController {


    @Autowired
    private ThesisService thesisService;

    @Autowired
    private ThesisRequestService thesisRequestService;

    @GetMapping("/create")
    public String showCreateThesisForm(Model model) {
        model.addAttribute("thesis", new ThesisDTO());
        List<ThesisRequestDTO> thesisRequests = thesisRequestService.getAllThesisRequests();
        model.addAttribute("thesisRequests", thesisRequests);
        return "theses/create-thesis";
    }

    @PostMapping("/create")
    public String createThesis(@Valid @ModelAttribute("thesis") ThesisDTO thesisDTO,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "theses/create-thesis";
        }
        thesisService.saveThesis(thesisDTO);
        return "redirect:/theses";
    }

    @GetMapping("/{id}")
    public String getThesisById(@PathVariable Long id, Model model) {
        ThesisDTO thesisDTO = thesisService.getThesisById(id);
        model.addAttribute("thesis", thesisDTO);
        return "theses/view-thesis";
    }

    @GetMapping
    public String getAllTheses(Model model) {
        List<ThesisDTO> theses = thesisService.getAllTheses();
        model.addAttribute("theses", theses);
        return "theses/list-theses";
    }

    @GetMapping("/{id}/edit")
    public String showEditThesisForm(@PathVariable Long id, Model model) {
        ThesisDTO thesisDTO = thesisService.getThesisById(id);
        List<ThesisRequestDTO> thesisRequests = thesisRequestService.getAllThesisRequests();
        model.addAttribute("thesis", thesisDTO);
        model.addAttribute("thesisRequests", thesisRequests);
        return "theses/edit-thesis";
    }


    @PostMapping("/{id}/edit")
    public String updateThesis(@PathVariable Long id,
                               @Valid @ModelAttribute("thesis") ThesisDTO thesisDTO,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "theses/edit-thesis";
        }
        thesisService.updateThesis(id, thesisDTO);
        return "redirect:/theses";
    }

    @PostMapping("/{id}/delete")
    public String deleteThesis(@PathVariable Long id) {
        thesisService.deleteThesis(id);
        return "redirect:/theses";
    }
}
