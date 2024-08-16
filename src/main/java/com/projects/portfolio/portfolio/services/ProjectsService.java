package com.projects.portfolio.portfolio.services;

import com.projects.portfolio.portfolio.constants.FileCons;
import com.projects.portfolio.portfolio.constants.ProjectConst;
import com.projects.portfolio.portfolio.models.Project;
import com.projects.portfolio.portfolio.models.ProjectDetails;
import com.projects.portfolio.portfolio.models.Skills;
import com.projects.portfolio.portfolio.models.dto.ProjectWithDetailsDTO;
import com.projects.portfolio.portfolio.repository.ProjectDetailsRepository;
import com.projects.portfolio.portfolio.repository.ProjectRepository;
import com.projects.portfolio.portfolio.repository.SkillsRepository;
import com.projects.portfolio.portfolio.services.storage_dapter.domain.StorageAdapter;
import com.projects.portfolio.portfolio.services.storage_dapter.utils.FileHandlers;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProjectsService {

   @Autowired
   ProjectRepository projectRepository;

   @Autowired
   ProjectDetailsRepository projectDetailsRepository;

   @Autowired
   SkillsRepository skillsRepository;

   @Autowired
   StorageAdapter storageAdapter;

   Logger logger = LoggerFactory.getLogger(ProjectsService.class);

   public List<Project> getProjects() {
      return projectRepository.findAll();
   }

   @Transactional
   public ProjectWithDetailsDTO saveProject(ProjectWithDetailsDTO projectWithDetails) {
      Project project = new Project();
      project.setName(projectWithDetails.getName());
      project.setPosition(projectWithDetails.getPosition());
      project.setType(projectWithDetails.getType());
      project.setFrom(projectWithDetails.getFrom());
      project.setTo(projectWithDetails.getTo());
      project.setSkills(validateSkills(projectWithDetails.getSkills()));

      try {
         // Save the project in the database
         project = projectRepository.save(project);
         logger.info("Project saved successfully: {}", project.getId());
      } catch (Exception e) {
         logger.error(e.getMessage());
         throw new RuntimeException("Error saving project");
      }

      try {
         // Fill the project details
         ProjectDetails projectDetails = new ProjectDetails();
         projectDetails.setDescription(projectWithDetails.getDescription());
         projectDetails.setGithub(projectWithDetails.getGithub());
         projectDetails.setLink(projectWithDetails.getLink());
         projectDetails.setProjects(project);

         // Save the project details in the database
         projectDetailsRepository.save(projectDetails);
         logger.info("Project details saved successfully");
      } catch (Exception e) {
         logger.error(e.getMessage());
         throw new RuntimeException("Error saving project details");
      }

      projectWithDetails.setId(project.getId());
      return projectWithDetails;
   }

   @Transactional ProjectWithDetailsDTO updateProject(ProjectWithDetailsDTO projectWithDetails, MultipartFile file) {
      Project project = projectRepository.findById(projectWithDetails.getId())
         .orElseThrow(() -> new RuntimeException("Project not found"));

      project.setName(projectWithDetails.getName());
      project.setPosition(projectWithDetails.getPosition());
      project.setType(projectWithDetails.getType());
      project.setFrom(projectWithDetails.getFrom());
      project.setTo(projectWithDetails.getTo());

      try {
         // Save the project in the database
         project = projectRepository.save(project);
      } catch (Exception e) {
         throw new RuntimeException("Error saving project");
      }

      try {
         // Fill the project details
         ProjectDetails projectDetails = projectDetailsRepository.findById(project.getId())
            .orElseThrow(() -> new RuntimeException("Project details not found"));

         projectDetails.setDescription(projectWithDetails.getDescription());
         projectDetails.setGithub(projectWithDetails.getGithub());
         projectDetails.setLink(projectWithDetails.getLink());
         projectDetails.setId(project.getId());

         // Save the project details in the database
         projectDetailsRepository.save(projectDetails);
      } catch (Exception e) {
         throw new RuntimeException("Error saving project details");
      }

      return projectWithDetails;
   }

   public String savePicture(MultipartFile file, String projectId) throws IOException {
      // Converting the MultipartFile to a File
      File fileConverted = FileHandlers.convertMultipartFileToFile(file);

      String fullPath = FileCons.PROJECTS_PATH
         .concat(projectId)
         .concat(FileCons.IMAGES_PATH);

      try {
         // Saving the file to the file system
         storageAdapter.uploadFile(fileConverted, file.getName(), fullPath);
      } catch (Exception e) {
         throw new RuntimeException("Error saving the picture");
      }

      return fullPath.concat(file.getName());
   }

   @Transactional
   private Set<Skills> validateSkills(List<String> skills) {
      Set<Skills> validSkills = new HashSet<>();

      // Check if the skills are valid
      for (String skill : skills) {
         if (!skill.isEmpty() || ProjectConst.containsSkill(skill)) {
            // Find a skill by name and add it to the list
            Skills tempSkill = skillsRepository.findByName(skill);
            if (tempSkill != null) {
               validSkills.add(tempSkill);
            }
         }
      }

      return validSkills;
   }
}
