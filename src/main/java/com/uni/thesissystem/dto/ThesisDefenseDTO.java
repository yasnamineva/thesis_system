package com.uni.thesissystem.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Data
public class ThesisDefenseDTO {
    private Long id;

    private List<Long> marks;

    @NotBlank
    private Long teacherId;

    @NotNull(message = "Thesis defense date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "The date has to be in the past!")
    private LocalDate date;

    @NotNull
    private Long thesisId;

    @NotBlank
    @Size(min = 5, max = 20, message="Min 5, Max 20")
    private String thesisTitle;
}
