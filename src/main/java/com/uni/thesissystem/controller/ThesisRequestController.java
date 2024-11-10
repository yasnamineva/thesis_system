package com.uni.thesissystem.controller;

import com.uni.thesissystem.dto.ThesisRequestDTO;
import com.uni.thesissystem.service.ThesisRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/thesis-requests")
public class ThesisRequestController {

    @Autowired
    private ThesisRequestService thesisRequestService;

    @GetMapping("/create")
    public String showCreateThesisRequestForm(Model model) {
        model.addAttribute("thesisRequest", new ThesisRequestDTO());
        return "thesis-requests/create-thesis-request";
    }

    @PostMapping("/create")
    public String createThesisRequest(@Valid @ModelAttribute("thesisRequest") ThesisRequestDTO thesisRequestDTO,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "thesis-requests/create-thesis-request";
        }
        thesisRequestService.saveThesisRequest(thesisRequestDTO);
        return "redirect:/thesis-requests";
    }

    @GetMapping("/{id}")
    public String getThesisRequestById(@PathVariable Long id, Model model) {
        ThesisRequestDTO thesisRequestDTO = thesisRequestService.getThesisRequestById(id);
        model.addAttribute("thesisRequest", thesisRequestDTO);
        return "thesis-requests/view-thesis-request";
    }

    @GetMapping
    public String getAllThesisRequests(Model model) {
        List<ThesisRequestDTO> thesisRequests = thesisRequestService.getAllThesisRequests();
        model.addAttribute("thesisRequests", thesisRequests);
        return "thesis-requests/list-thesis-requests";
    }

    @GetMapping("/{id}/edit")
    public String showEditThesisRequestForm(@PathVariable Long id, Model model) {
        ThesisRequestDTO thesisRequestDTO = thesisRequestService.getThesisRequestById(id);
        model.addAttribute("thesisRequest", thesisRequestDTO);
        return "thesis-requests/edit-thesis-request";
    }

    @PostMapping("/{id}/edit")
    public String updateThesisRequest(@PathVariable Long id,
                                      @Valid @ModelAttribute("thesisRequest") ThesisRequestDTO thesisRequestDTO,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "thesis-requests/edit-thesis-request";
        }
        thesisRequestService.updateThesisRequest(id, thesisRequestDTO);
        return "redirect:/thesis-requests";
    }

    @PostMapping("/{id}/delete")
    public String deleteThesisRequest(@PathVariable Long id) {
        thesisRequestService.deleteThesisRequest(id);
        return "redirect:/thesis-requests";
    }
}
