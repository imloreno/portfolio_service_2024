package com.projects.portfolio.portfolio.services.storage_dapter;

import com.projects.portfolio.portfolio.services.storage_dapter.domain.StorageAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Service
@Profile("DEV")
public class Local implements StorageAdapter {

   Logger logger = LoggerFactory.getLogger(Files.class);

   @Override
   // Take the file and save it to the path in the local file system
   public boolean uploadFile(File file, String fileName, String path) throws IOException {

      Path filePath = Paths.get(path, fileName);
      Path directoryPath = Paths.get(path);

      // Ensuring the directory exists
      if (!Files.exists(directoryPath)) {
         Files.createDirectories(directoryPath);
      }

      try {
         Files.move(file.toPath(), filePath, StandardCopyOption.REPLACE_EXISTING);
         logger.info("File uploaded successfully to: " + filePath);
      } catch (IOException e) {
         logger.error("Error uploading file: " + e.getMessage());
         return false;
      }
      return true;

   }

   @Override
   public Resource getInputStream(String path) throws IOException {
      // Define the path to the image
      Path url = Paths.get(path);
      Resource resource = new UrlResource(url.toUri());

      // Check if the image exists
      if (!resource.exists()) {
         throw new IOException("File not found");
      }

      return resource;
   }

   @Override
   public boolean deleteFile(String fileName, String path) {
      return false;
   }
}
