package com.projects.portfolio.portfolio.services;

import com.projects.portfolio.portfolio.repository.ProjectGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

@Service
public class GalleryService {

   @Autowired
   ProjectGalleryRepository projectGalleryRepository;

   public void addPictureToGallery(UUID projectId, File picture) {
      ProjectGallery projectGallery = new ProjectGallery();
      projectGallery.setProject(projectRepository.findById(projectId).

}
