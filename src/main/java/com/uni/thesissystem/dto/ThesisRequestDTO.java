package com.uni.thesissystem.dto;

import lombok.Data;

@Data
public class ThesisRequestDTO {
    private Long id;
    private String title;
    private String description;
    private Long studentId;
    private Long supervisorId;
}
