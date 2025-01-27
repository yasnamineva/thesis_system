package com.uni.thesissystem.controller;


import com.uni.thesissystem.dto.StudentDTO;
import com.uni.thesissystem.dto.TeacherDTO;
import com.uni.thesissystem.dto.ThesisDTO;
import com.uni.thesissystem.dto.ThesisRequestDTO;
import com.uni.thesissystem.security.CustomUserDetails;
import com.uni.thesissystem.service.StudentService;
import com.uni.thesissystem.service.TeacherService;
import com.uni.thesissystem.service.ThesisRequestService;
import com.uni.thesissystem.serviceImpl.ThesisRequestServiceImpl;
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
    private ThesisRequestServiceImpl thesisRequestService;
    @Autowired
    private ThesisServiceImpl thesisService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

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
