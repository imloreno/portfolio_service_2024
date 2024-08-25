package com.projects.portfolio.portfolio.services.storage_dapter.domain;

import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

public interface StorageAdapter {
   boolean uploadFile(File file, String fileName, String path) throws IOException;
   Resource getInputStream(String path) throws IOException;
   boolean deleteFile(String fileName, String path);
}
