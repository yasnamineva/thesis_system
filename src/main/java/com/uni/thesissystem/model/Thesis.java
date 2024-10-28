package com.uni.thesissystem.model;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thesis extends IdGenerator {
    private LocalDate submissionDate;
    private String title;

    @OneToOne
    @JoinColumn(name = "request_id")
    private ThesisRequest request;
}