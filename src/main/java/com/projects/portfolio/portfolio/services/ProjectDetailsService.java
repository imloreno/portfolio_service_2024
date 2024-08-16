package com.projects.portfolio.portfolio.services;

import com.projects.portfolio.portfolio.models.ProjectDetails;
import com.projects.portfolio.portfolio.repository.ProjectDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProjectDetailsService {

   @Autowired
   ProjectDetailsRepository projectDetailsRepository;

   public ProjectDetails getProjectDetails(UUID id) {
      return projectDetailsRepository
         .findById(id)
         .orElseThrow( () -> new RuntimeException("Project details not found"));
   }

}
