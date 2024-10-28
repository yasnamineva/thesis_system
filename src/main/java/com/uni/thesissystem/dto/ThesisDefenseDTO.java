package com.uni.thesissystem.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ThesisDefenseDTO {
    private Long id;
    private List<Long> marks;
    private List<Long> teacherIds;
    private LocalDate date;
    private Long thesisId;
}
