package com.uni.thesissystem.model;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thesis extends IdGenerator {
    private Date submitionDate;
    private String title;

    @OneToOne
    @JoinColumn(name = "request_id")
    private ThesisRequest request;


}
