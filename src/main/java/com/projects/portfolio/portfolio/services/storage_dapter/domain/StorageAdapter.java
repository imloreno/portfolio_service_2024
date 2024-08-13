package com.projects.portfolio.portfolio.services.storage_dapter.domain;

import java.io.File;
import java.io.IOException;

public interface StorageAdapter {
   boolean uploadFile(File file, String fileName, String path) throws IOException;
   File getFile(String fileName, String path) throws IOException;
   boolean deleteFile(String fileName, String path);
}
