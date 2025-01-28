package com.uni.thesissystem.controller;

import com.uni.thesissystem.dto.*;
import com.uni.thesissystem.security.CustomUserDetails;
import com.uni.thesissystem.service.*;
import com.uni.thesissystem.serviceImpl.ThesisRequestServiceImpl;
import com.uni.thesissystem.serviceImpl.ThesisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private ThesisRequestService thesisRequestService;
    @Autowired
    private ThesisService thesisService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ThesisDefenseService thesisDefenseService;

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

                if (associatedThesis != null) {
                    ThesisDefenseDTO associatedDefense = thesisDefenseService.getAllThesisDefenses()
                            .stream()
                            .filter(defense -> defense.getThesisId().equals(associatedThesis.getId()))
                            .findFirst()
                            .orElse(null);

                    if (associatedDefense != null && associatedDefense.getMarks() != null) {
                        String marksAsString = associatedDefense.getMarks().stream()
                                .map(String::valueOf)
                                .flatMap(mark -> mark.chars()
                                        .mapToObj(c -> String.valueOf((char) c)))
                                .collect(Collectors.joining(", "));
                        model.addAttribute("marksAsString", marksAsString);
                    } else {
                        model.addAttribute("marksAsString", "No marks");
                    }

                    model.addAttribute("associatedDefense", associatedDefense);
                }

                model.addAttribute("thesisRequest", thesisRequest);
                model.addAttribute("associatedThesis", associatedThesis);
            }

            return "dashboard/student";
        } else {
            model.addAttribute("error", "Invalid user details or not authenticated.");
            return "error";
        }
    }

    @GetMapping("/admin")
    public String showAdminDashboard(Authentication authentication, Model model) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

            if (customUserDetails.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
                return "dashboard/admin";
            } else {
                model.addAttribute("error", "Access Denied. You do not have admin privileges.");
                return "error";
            }
        } else {
            model.addAttribute("error", "Invalid user details or not authenticated.");
            return "error";
        }
    }
}
