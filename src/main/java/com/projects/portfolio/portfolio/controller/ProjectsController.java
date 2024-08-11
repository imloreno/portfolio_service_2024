package com.projects.portfolio.portfolio.controller;

import com.projects.portfolio.portfolio.models.Project;
import com.projects.portfolio.portfolio.models.dao.ResponseEntityDAO;
import com.projects.portfolio.portfolio.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectsController {

   @Autowired
   ProjectsService projectsService;

   @GetMapping
   public ResponseEntityDAO<List<Project>> getProjects() {
      return new ResponseEntityDAO<>("Projects retrieved successfully", 200, projectsService.getProjects());
   }

   @PostMapping
   public ResponseEntityDAO<Project> saveProject(@Validated @RequestBody Project project) {
      return new ResponseEntityDAO<>("Project saved successfully", 200, projectsService.saveProject(project));
   }

}
