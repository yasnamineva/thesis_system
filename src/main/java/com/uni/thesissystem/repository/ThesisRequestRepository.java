package com.uni.thesissystem.repository;

import com.uni.thesissystem.model.ThesisRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThesisRequestRepository extends JpaRepository<ThesisRequest, Long> {
    boolean existsByStudentId(Long studentId);
    List<ThesisRequest> findByStudentId(Long studentId);

}
