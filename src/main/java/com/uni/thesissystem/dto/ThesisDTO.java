package com.uni.thesissystem.dto;

import lombok.Data;

@Data
public class ThesisDTO {
    private Long id;
    private String title;
    private String description;
    private StudentDTO student;
}
