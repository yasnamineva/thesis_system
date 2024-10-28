package com.uni.thesissystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recension extends IdGenerator{

    private LocalDate submissionDate;
    private boolean conclusion;

    @OneToOne
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;

}
