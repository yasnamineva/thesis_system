package com.uni.thesissystem.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class RecensionDTO {

    private Long id;

    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message="The date has to be in the past!")
    private LocalDate submissionDate;

    private Boolean conclusion;

    @NotNull
    private Long thesisId;
}
