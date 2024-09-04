package com.projects.portfolio.portfolio.services;

import com.projects.portfolio.portfolio.constants.FileCons;
import com.projects.portfolio.portfolio.helpers.FileHelpers;
import com.projects.portfolio.portfolio.models.Project;
import com.projects.portfolio.portfolio.models.ProjectGallery;
import com.projects.portfolio.portfolio.repository.ProjectGalleryRepository;
import com.projects.portfolio.portfolio.repository.ProjectRepository;
import com.projects.portfolio.portfolio.services.storage_dapter.domain.StorageAdapter;
import com.projects.portfolio.portfolio.services.storage_dapter.utils.FileHandlers;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class GalleryService {

   @Autowired
   ProjectGalleryRepository projectGalleryRepository;

   @Autowired
   ProjectRepository projectRepository;

   @Autowired
   StorageAdapter storageAdapter;

   @Value("${files.base-dir}")
   private String baseDir;

   Logger logger = LoggerFactory.getLogger(GalleryService.class);

   @Transactional
   public void addPictureToGallery(UUID projectId, File picture) {
      Project targetProject = projectRepository.findById(projectId).orElseThrow(
         () -> new IllegalArgumentException("Project with id " + projectId + " not found")
      );

      ProjectGallery projectGallery = new ProjectGallery();
      projectGallery.setProject(targetProject);

      try {
         ProjectGallery photo = projectGalleryRepository.save(projectGallery);
         // Build path to save picture
         String relativePath = FileCons.GALLERY_FULL_PATH
            .replace(FileCons.ID_SUFIX, projectId.toString());
         // Save picture to gallery
         storageAdapter.uploadFile(picture, picture.getName() , relativePath);
         logger.info("Picture saved to gallery: {}", picture.getName());
      } catch (Exception e) {
         logger.error("Failed to save picture to gallery: {}", e.getMessage());
         throw new IllegalArgumentException("Failed to save picture to gallery");
      }
   }

   public List<ProjectGallery> getGallery(UUID projectId) {
      return projectGalleryRepository.findByProjectId(projectId);
   }

   @Transactional
   public String saveToGallery(MultipartFile file, String projectId) throws IOException {
      // Converting the MultipartFile to a File
      File fileConverted = FileHandlers.convertMultipartFileToFile(file);
      // Define the relative path
      String relativePath = FileCons.GALLERY_FULL_PATH
              .replace(FileCons.ID_SUFIX, projectId.toString());
      // Define the target project
      Project targetProject = projectRepository.findById(UUID.fromString(projectId))
              .orElseThrow(() -> new RuntimeException("Project not found"));
      // File extension
      String fileExtension = ".".concat(FileHelpers.getFileExtension(fileConverted.getName()));
      // Defining the project gallery
      ProjectGallery projectGallery = new ProjectGallery();
      projectGallery.setProject(targetProject);

      // Save the file to the file system and update the Project picture attribute
      try {
         // Add gallery picture to the project
         projectGallery = projectGalleryRepository.save(projectGallery);
         // Saving the file to the file system
         storageAdapter.uploadFile(
                 fileConverted,
                 projectGallery.getId().toString().concat(fileExtension),
                 baseDir.concat(relativePath));

         projectGallery.setName(projectGallery.getId().toString().concat(fileExtension));
         projectGalleryRepository.save(projectGallery);
      } catch (Exception e) {
         throw new RuntimeException("Error saving the picture");
      }

      return projectGallery.getId().toString().concat(fileExtension);
   }

   public Resource getProfileImage(UUID id) throws IOException {

      // Check if the project exists
      Project project = projectRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Project ID not valid: ".concat(id.toString())));

      // Define the path to the image
      String path = baseDir.concat(project.getPicture());
      Resource resource = storageAdapter.getInputStream(path);

      if (resource == null) {
         throw new RuntimeException("Image not found");
      }

      return resource;
   }

   public Resource getGalleryImage(String projectId, String fileName) throws IOException {

      // Define the path to the image
      String path = baseDir.concat(FileCons.GALLERY_FULL_PATH.replace(FileCons.ID_SUFIX, projectId)).concat(fileName);
      Resource resource = storageAdapter.getInputStream(path);

      if (resource == null) {
         throw new RuntimeException("Image not found");
      }

      return resource;
   }
}
