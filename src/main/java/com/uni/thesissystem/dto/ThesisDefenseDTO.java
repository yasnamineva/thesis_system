package com.uni.thesissystem.dto;

import lombok.Data;
import java.util.List;

@Data
public class ThesisDefenseDTO {
    private Long id;
    private List<Integer> marks;
    private List<TeacherDTO> teachers;
}
