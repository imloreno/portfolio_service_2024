package com.projects.portfolio.portfolio.controller;

import com.projects.portfolio.portfolio.models.Project;
import com.projects.portfolio.portfolio.models.ProjectDetails;
import com.projects.portfolio.portfolio.models.dto.ProjectWithDetailsDTO;
import com.projects.portfolio.portfolio.models.dto.ResponseEntityDTO;
import com.projects.portfolio.portfolio.services.ProjectDetailsService;
import com.projects.portfolio.portfolio.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectsController {

   @Autowired
   ProjectsService projectsService;

   @Autowired
   ProjectDetailsService projectDetailsService;

   @GetMapping
   public ResponseEntityDTO<List<Project>> getProjects() {
      return new ResponseEntityDTO<>(
         "Projects retrieved successfully",
         HttpStatus.OK.value(),
         projectsService.getProjects());
   }

   @GetMapping("/details/{id}")
   public ResponseEntityDTO<ProjectDetails> getProjects(@PathVariable UUID id) {

      ProjectDetails projectDetails;

      try {
         projectDetails = projectDetailsService.getProjectDetails(id);
      } catch (RuntimeException e) {
         return new ResponseEntityDTO<>(
            e.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            null);
      }

      return new ResponseEntityDTO<>(
         "Project details retrieved from:" + id,
         HttpStatus.OK.value(),
         projectDetails);
   }

   @GetMapping(value = "/details/{id}/profile", produces = MediaType.IMAGE_JPEG_VALUE)
   public Resource getProfileImage(@PathVariable UUID id) throws IOException {
      return projectsService.getProfileImage(id);
   }

   @Profile("DEV")
   @PostMapping
   public ResponseEntityDTO<ProjectWithDetailsDTO> saveProject(@Validated @RequestBody ProjectWithDetailsDTO projectWithDetails) {
      ProjectWithDetailsDTO projectWithDetailsDTO;

      try {
         // Saving the project
         projectWithDetailsDTO = projectsService.saveProject(projectWithDetails);


      } catch (Exception e) {
         return new ResponseEntityDTO<>(
            e.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            null);
      }

      return new ResponseEntityDTO<>(
         "Project saved successfully",
         HttpStatus.CREATED.value(),
         projectWithDetailsDTO
      );
   }

}
