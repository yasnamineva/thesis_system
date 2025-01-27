package com.uni.thesissystem.controller;


import com.uni.thesissystem.dto.ThesisDTO;
import com.uni.thesissystem.dto.ThesisRequestDTO;
import com.uni.thesissystem.security.CustomUserDetails;
import com.uni.thesissystem.service.ThesisRequestService;
import com.uni.thesissystem.serviceImpl.ThesisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private ThesisRequestService thesisRequestService;

    @Autowired
    private ThesisServiceImpl thesisService;

    @GetMapping("/student")
    public String showDashboard(Authentication authentication, Model model) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            Long studentId = customUserDetails.getId();

            ThesisRequestDTO thesisRequest = thesisRequestService.getRequestsForStudent(studentId)
                    .stream()
                    .findFirst()
                    .orElse(null);

            if (thesisRequest != null) {
                ThesisDTO associatedThesis = thesisService.getThesisByRequestId(thesisRequest.getId());

                model.addAttribute("thesisRequest", thesisRequest);
                model.addAttribute("associatedThesis", associatedThesis);
            }

            return "dashboard/student";
        } else {
            model.addAttribute("error", "Invalid user details or not authenticated.");
            return "error";
        }
    }




}
