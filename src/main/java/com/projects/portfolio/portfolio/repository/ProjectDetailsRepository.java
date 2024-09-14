package com.projects.portfolio.portfolio.repository;

import com.projects.portfolio.portfolio.models.ProjectDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectDetailsRepository extends JpaRepository<ProjectDetails, UUID>, JpaSpecificationExecutor<ProjectDetails> {
}
