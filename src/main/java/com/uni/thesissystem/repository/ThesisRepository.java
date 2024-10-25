package com.uni.thesissystem.repository;

import com.uni.thesissystem.model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "thesis") //caused by a typo generated in the links
public interface ThesisRepository extends JpaRepository<Thesis, Long> {

}
