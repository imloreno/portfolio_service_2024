package com.projects.portfolio.portfolio.services.storage_dapter;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.projects.portfolio.portfolio.services.storage_dapter.domain.StorageAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
   public File getFile(String fileName, String path) throws IOException {
      String fullPath = path.concat(fileName);
      S3Object s3Object = s3Client.getObject(bucketName, fullPath); // Get the S3 object
      S3ObjectInputStream inputStream = s3Object.getObjectContent(); // Get the S3 object content

      // Create a temporary file
      File file = File.createTempFile("s3_", "_file");

      try (FileOutputStream outputStream = new FileOutputStream(file)) {
         byte[] buffer = new byte[4096];
         int bytesRead;

         // Read from S3 object and write to file
         while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
         }
      } finally {
         // Close the input stream
         inputStream.close();
         s3Object.close();
      }

      return file;
   }

   @Override
   public boolean deleteFile(String fileName, String path) {
      return false;
   }
}