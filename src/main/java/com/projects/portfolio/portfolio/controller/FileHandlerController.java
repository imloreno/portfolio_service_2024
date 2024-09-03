package com.projects.portfolio.portfolio.controller;

import com.projects.portfolio.portfolio.constants.FileCons;
import com.projects.portfolio.portfolio.models.Project;
import com.projects.portfolio.portfolio.models.dto.ResponseEntityDTO;
import com.projects.portfolio.portfolio.repository.ProjectRepository;
import com.projects.portfolio.portfolio.services.ProjectsService;
import com.projects.portfolio.portfolio.services.storage_dapter.domain.StorageAdapter;
import com.projects.portfolio.portfolio.services.storage_dapter.utils.FileHandlers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/file")
public class FileHandlerController {

   @Autowired
   StorageAdapter storageAdapter;

   @Autowired
   ProjectsService projectsService;

   @Autowired
   ProjectRepository projectRepository;

   @Value("${files.base-dir}")
   private String baseDir;

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

   @PostMapping("/uploadFiles/{projectId}")
   public String uploadFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("projectId") UUID projectId) {

      Arrays.stream(files)
         .forEach(file -> {
            try {
               projectsService.saveToGallery(file, projectId.toString());
            } catch (IOException e) {
               throw new RuntimeException(e);
            }
         });

      return "redirect:/";
   }
}
