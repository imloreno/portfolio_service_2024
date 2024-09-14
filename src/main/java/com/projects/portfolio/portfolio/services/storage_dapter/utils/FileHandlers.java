package com.projects.portfolio.portfolio.services.storage_dapter.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public final class FileHandlers {
   public static File convertMultipartFileToFile(MultipartFile file) throws IOException {
      File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
      try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
         fos.write(file.getBytes());
      }
      return convertedFile;
   }
}
