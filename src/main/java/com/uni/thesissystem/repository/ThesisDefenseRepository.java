package com.uni.thesissystem.repository;

import com.uni.thesissystem.model.ThesisDefense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ThesisDefenseRepository extends JpaRepository<ThesisDefense, Long> {
    ThesisDefense findByThesisId(Long thesisId);
}
