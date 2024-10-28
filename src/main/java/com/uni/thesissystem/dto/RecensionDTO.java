package com.uni.thesissystem.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RecensionDTO {
    private Long id;
    private LocalDate submissionDate;
    private Boolean conclusion;
    private Long thesisId;
}
