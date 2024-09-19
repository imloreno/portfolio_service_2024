package com.projects.portfolio.portfolio.services.storage_dapter;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.projects.portfolio.portfolio.services.storage_dapter.domain.StorageAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Profile("PROD")
public class S3 implements StorageAdapter {

   private static final Logger logger = LoggerFactory.getLogger(AmazonS3.class);

   @Autowired
   AmazonS3 s3Client;

   @Value("${aws.bucket-name}")
   private String bucketName;

   @Override
   public boolean uploadFile(File file, String fileName, String path) {
      String fullPath = path.concat(fileName);
      PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fullPath, file);

      try {
         s3Client.putObject(putObjectRequest);
         logger.info("File added ".concat(fullPath).concat(" in bucket ").concat(bucketName));
      } catch (AmazonServiceException e) {
         logger.error("Error uploading file ".concat(fullPath).concat(" in bucket ").concat(bucketName));
         System.out.println(e.getErrorMessage());
         return false;
      }

      // Delete the file after uploading to avoid filling up the disk
      file.delete();
      return true;
   }

   @Override
   public boolean deleteFile(String fileName, String path) {
      return false;
   }

   @Override
   public Resource getInputStream(String objectKey) {
      S3Object s3Object = s3Client.getObject(bucketName, objectKey);
      Resource resource;

      try {
         resource = new InputStreamResource(s3Object.getObjectContent());
      } catch (Exception e) {
         logger.error("Error getting file ".concat(objectKey).concat(" from bucket ").concat(bucketName));
         return null;
      }
      return resource;
   }
}