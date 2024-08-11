package com.projects.portfolio.portfolio.services;

import com.projects.portfolio.portfolio.models.Project;
import com.projects.portfolio.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsService {

   @Autowired
   ProjectRepository projectRepository;

   public List<Project> getProjects() {
      return projectRepository.findAll();
   }

   public Project saveProject(Project project) {
      return projectRepository.save(project);
   }
}
