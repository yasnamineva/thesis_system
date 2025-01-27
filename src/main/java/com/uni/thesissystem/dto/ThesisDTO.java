package com.uni.thesissystem.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class ThesisDTO {
    private Long id;

    @NotNull(message = "Submission date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "The date has to be in the past!")
    private LocalDate submissionDate;

    @NotBlank
    @Size(min = 5, max = 20, message="Min 5, Max 20")
    private String title;

    @NotNull
    private Long requestId;
}
