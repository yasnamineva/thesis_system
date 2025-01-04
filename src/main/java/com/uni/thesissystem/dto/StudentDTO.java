package com.uni.thesissystem.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StudentDTO {
    private Long id;

    @NotBlank
    private String name;
}
