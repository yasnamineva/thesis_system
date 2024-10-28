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
    private List<Long> marks;

    @ManyToMany
    @JoinTable(name = "defense_teachers",
            joinColumns = @JoinColumn(name = "defense_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<Teacher> teachers;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;
}
