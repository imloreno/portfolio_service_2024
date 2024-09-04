package com.projects.portfolio.portfolio.repository;

import com.projects.portfolio.portfolio.models.ProjectGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectGalleryRepository extends JpaRepository<ProjectGallery, UUID>, JpaSpecificationExecutor<ProjectGallery>{
    List<ProjectGallery> findByProjectId(UUID projectId);
}
