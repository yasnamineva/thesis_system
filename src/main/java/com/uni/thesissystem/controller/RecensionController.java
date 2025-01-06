package com.uni.thesissystem.controller;

import com.uni.thesissystem.dto.RecensionDTO;
import com.uni.thesissystem.dto.ThesisDTO;
import com.uni.thesissystem.service.RecensionService;
import javax.validation.Valid;

import com.uni.thesissystem.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/recensions")
public class RecensionController {
    @Autowired
    private RecensionService recensionService;

    @Autowired
    private ThesisService thesisService;

    @GetMapping("/create")
    public String showCreateRecensionForm(Model model) {
        model.addAttribute("recension", new RecensionDTO());
        List<ThesisDTO> theses = thesisService.getAllTheses();
        model.addAttribute("theses", theses);
        return "recensions/create-recension";
    }

    @PostMapping("/create")
    public String createRecension(@Valid @ModelAttribute("recension") RecensionDTO recensionDTO,
                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<ThesisDTO> theses = thesisService.getAllTheses();
            model.addAttribute("theses", theses);
            return "recensions/create-recension";
        }
        recensionService.saveRecension(recensionDTO);
        return "redirect:/recensions";
    }

    @GetMapping("/{id}")
    public String getRecensionById(@PathVariable Long id, Model model) {
        RecensionDTO recensionDTO = recensionService.getRecensionById(id);
        model.addAttribute("recension", recensionDTO);
        return "recensions/view-recension";
    }

    @GetMapping
    public String getAllRecensions(Model model) {
        List<RecensionDTO> recensions = recensionService.getAllRecensions();
        Map<Long, String> thesisTitles = new HashMap<>();

        for (RecensionDTO recension : recensions) {
            ThesisDTO thesis = thesisService.getThesisById(recension.getThesisId());
            if (thesis != null) {
                thesisTitles.put(recension.getThesisId(), thesis.getTitle());
            }
        }

        model.addAttribute("recensions", recensions);
        model.addAttribute("thesisTitles", thesisTitles);

        return "recensions/list-recensions";
    }



    @GetMapping("/{id}/edit")
    public String showEditRecensionForm(@PathVariable Long id, Model model) {
        RecensionDTO recensionDTO = recensionService.getRecensionById(id);
        model.addAttribute("recension", recensionDTO);
        return "recensions/edit-recension";
    }

    @PostMapping("/{id}/edit")
    public String updateRecension(@PathVariable Long id,
                                  @Valid @ModelAttribute("recension") RecensionDTO recensionDTO,
                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "recensions/edit-recension";
        }
        recensionService.updateRecension(id, recensionDTO);
        return "redirect:/recensions";
    }

    @PostMapping("/{id}/delete")
    public String deleteRecension(@PathVariable Long id) {
        recensionService.deleteRecension(id);
        return "redirect:/recensions";
    }
}
