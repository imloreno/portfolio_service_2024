package com.projects.portfolio.portfolio.services.storage_dapter.domain;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.web.multipart.MultipartFile;

public interface StorageAdapter {
   public void uploadFile(MultipartFile file, String fileName, String path);

   public S3Object getFile(String fileName, String path);
}
