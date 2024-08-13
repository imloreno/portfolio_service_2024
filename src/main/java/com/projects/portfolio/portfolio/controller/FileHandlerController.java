package com.projects.portfolio.portfolio.controller;

import com.projects.portfolio.portfolio.constants.FileCons;
import com.projects.portfolio.portfolio.services.storage_dapter.domain.StorageAdapter;
import com.projects.portfolio.portfolio.services.storage_dapter.utils.FileHandlers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/file")
public class FileHandlerController {

   @Autowired
   StorageAdapter storageAdapter;

   @Value("${files.base-dir}")
   private String baseDir;

   @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
   public void uploadFile(@Validated @RequestParam("custom_file") MultipartFile file) throws IOException {
      File convertedFile = FileHandlers.convertMultipartFileToFile(file);
      storageAdapter.uploadFile(convertedFile, "picture.jpg", baseDir.concat(FileCons.IMAGES_PATH));
   }

}
