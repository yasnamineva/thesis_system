package com.uni.thesissystem.repository;

import com.uni.thesissystem.model.ThesisRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisRequestRepository extends JpaRepository<ThesisRequest, Long> {
    boolean existsByStudentId(Long studentId);
}
