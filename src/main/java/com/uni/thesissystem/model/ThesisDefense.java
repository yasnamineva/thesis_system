package com.uni.thesissystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThesisDefense extends IdGenerator {

    @ElementCollection
    @CollectionTable(name = "thesis_defense_marks", joinColumns = @JoinColumn(name = "thesis_defense_id"))
    private List<Long> marks;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "thesis_id", nullable = false)
    private Thesis thesis;
}

