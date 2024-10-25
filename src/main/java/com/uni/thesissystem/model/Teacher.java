package com.uni.thesissystem.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;

import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends IdGenerator{

    private String name;

    @ManyToMany(mappedBy = "teachers")
    @JsonIgnore
    private List<ThesisDefense> defenses;

}