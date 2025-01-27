package com.uni.thesissystem.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ThesisRequestDTO {
    private Long id;

    @NotBlank
    @Size(min = 5, max = 30, message="Min 5, Max 30")
    private String title;

    @NotBlank
    @Size(min = 5, max = 30, message="Min 5, Max 30")
    private String description;

    @NotNull
    private Long studentId;

    @NotNull
    private Long supervisorId;

    private List<String> technologies;

    private boolean isApproved;
}
