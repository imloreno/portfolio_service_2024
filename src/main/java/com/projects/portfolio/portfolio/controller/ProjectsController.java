package com.projects.portfolio.portfolio.controller;

import com.projects.portfolio.portfolio.constants.FileCons;
import com.projects.portfolio.portfolio.models.Project;
import com.projects.portfolio.portfolio.models.dto.ProjectWithDetailsDTO;
import com.projects.portfolio.portfolio.models.dto.ResponseEntityDTO;
import com.projects.portfolio.portfolio.services.ProjectsService;
import com.projects.portfolio.portfolio.services.storage_dapter.domain.StorageAdapter;
import com.projects.portfolio.portfolio.services.storage_dapter.utils.FileHandlers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
