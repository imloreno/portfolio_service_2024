package com.projects.portfolio.portfolio.controller;

import com.projects.portfolio.portfolio.constants.FileCons;
import com.projects.portfolio.portfolio.models.Project;
import com.projects.portfolio.portfolio.models.ProjectGallery;
import com.projects.portfolio.portfolio.models.dto.ResponseEntityDTO;
import com.projects.portfolio.portfolio.repository.ProjectGalleryRepository;
import com.projects.portfolio.portfolio.repository.ProjectRepository;
import com.projects.portfolio.portfolio.services.GalleryService;
import com.projects.portfolio.portfolio.services.ProjectsService;
import com.projects.portfolio.portfolio.services.storage_dapter.domain.StorageAdapter;
import com.projects.portfolio.portfolio.services.storage_dapter.utils.FileHandlers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/file")
public class FileHandlerController {

   @Autowired
   StorageAdapter storageAdapter;

   @Autowired
   ProjectsService projectsService;

   @Autowired
   GalleryService galleryService;

   @Autowired
   ProjectGalleryRepository projectGalleryRepository;

   @Value("${files.base-dir}")
   private String baseDir;


   @GetMapping(value = "/details/{id}/profile", produces = MediaType.IMAGE_JPEG_VALUE)
   public Resource getProfileImage(@PathVariable UUID id) throws IOException {
      return galleryService.getProfileImage(id);
   }

   @PostMapping
   @Profile("DEV")
   public void uploadFile(@Validated @RequestParam("custom_file") MultipartFile file) throws IOException {
      File convertedFile = FileHandlers.convertMultipartFileToFile(file);
      storageAdapter.uploadFile(convertedFile, "picture.jpg", baseDir.concat(FileCons.IMAGES_PATH));
   }

   // Upload file to the profile according project id
   @PostMapping("/profile")
   public ResponseEntityDTO<String> uploadProfilePicture(@RequestParam("project_id") UUID projectId, @RequestParam("profile_picture") MultipartFile file) throws IOException {
      String picUploaded = projectsService.savePicture(file, projectId.toString());

      return new ResponseEntityDTO<>(
         "Profile picture uploaded successfully",
         200,
         picUploaded);
   }

   @PostMapping("/uploadFiles")
   public ResponseEntityDTO<String> uploadFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("projectId") String projectId) {

      Arrays.stream(files).forEach(file -> {
         try{
            galleryService.saveToGallery(file, projectId);
         } catch (IOException e) {
            e.printStackTrace();
         }
      });

      return new ResponseEntityDTO<>(
              "Profile picture uploaded successfully",
              200,
              "Images uploaded successfully");
   }

   @GetMapping("/project-gallery/{projectId}")
   public ResponseEntityDTO<List<ProjectGallery>> getProjectGallery(@PathVariable("projectId") UUID projectId) {
      List<ProjectGallery> projectGallery = projectGalleryRepository.findByProjectId(projectId);

      return new ResponseEntityDTO<>(
         "Project gallery retrieved successfully",
         200,
         projectGallery);
   }

   @GetMapping(value = "/project-gallery/{projectId}/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
   public Resource getGalleryImage(@PathVariable("projectId") String projectId, @PathVariable("fileName") String fileName) throws IOException {
      return galleryService.getGalleryImage(projectId, fileName);
   }
}
