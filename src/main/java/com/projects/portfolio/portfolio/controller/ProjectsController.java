package com.projects.portfolio.portfolio.controller;

import com.projects.portfolio.portfolio.models.Project;
import com.projects.portfolio.portfolio.models.dto.ResponseEntityDTO;
import com.projects.portfolio.portfolio.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectsController {

   @Autowired
   ProjectsService projectsService;

   @GetMapping
   public ResponseEntityDTO<List<Project>> getProjects() {
      return new ResponseEntityDTO<>(
         "Projects retrieved successfully",
         HttpStatus.OK.value(),
         projectsService.getProjects());
   }

   @PostMapping
   public ResponseEntityDTO<Project> saveProject(@Validated @RequestBody Project project) {
      return new ResponseEntityDTO<>(
         "Project saved successfully",
         HttpStatus.OK.value(),
         projectsService.saveProject(project));
   }

}
