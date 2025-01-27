package com.uni.thesissystem.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class StudentDTO {
    private Long id;

    @NotBlank
    @Size(min = 5, max = 20, message="Min 5, Max 20")
    private String name;
}
