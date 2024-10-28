package com.uni.thesissystem.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ThesisDTO {
    private Long id;
    private LocalDate submissionDate;
    private String title;
    private Long requestId;
}
