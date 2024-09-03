package com.projects.portfolio.portfolio.services;

import com.projects.portfolio.portfolio.constants.FileCons;
import com.projects.portfolio.portfolio.models.Project;
import com.projects.portfolio.portfolio.models.ProjectGallery;
import com.projects.portfolio.portfolio.repository.ProjectGalleryRepository;
import com.projects.portfolio.portfolio.repository.ProjectRepository;
import com.projects.portfolio.portfolio.services.storage_dapter.domain.StorageAdapter;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

@Service
public class GalleryService {

   @Autowired
   ProjectGalleryRepository projectGalleryRepository;

   @Autowired
   ProjectRepository projectRepository;

   @Autowired
   StorageAdapter storageAdapter;

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

}
