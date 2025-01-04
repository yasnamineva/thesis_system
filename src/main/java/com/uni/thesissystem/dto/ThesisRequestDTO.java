package com.uni.thesissystem.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ThesisRequestDTO {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Long studentId;

    @NotNull
    private Long supervisorId;

    private List<String> technologies;

    private boolean isApproved;
}
