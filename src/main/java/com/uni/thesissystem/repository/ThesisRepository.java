package com.uni.thesissystem.repository;

import com.uni.thesissystem.model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "thesis")
public interface ThesisRepository extends JpaRepository<Thesis, Long> {
    Optional<Thesis> findByRequestId(Long requestId);
}
