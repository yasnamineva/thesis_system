package com.uni.thesissystem.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Data
public class ThesisDefenseDTO {
    private Long id;

    private List<Long> marks;

    @UniqueElements
    @NotBlank
    private List<Long> teacherIds;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    private Long thesisId;
}
