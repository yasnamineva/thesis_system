package com.uni.thesissystem.dto;

import lombok.Data;

@Data
public class RecensionDTO {
    private Long id;
    private String content;
    private TeacherDTO professor;
    private ThesisDTO thesis;
}
