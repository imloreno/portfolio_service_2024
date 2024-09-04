package com.projects.portfolio.portfolio.helpers;

public final class FileHelpers {
    public static String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf(".") == -1) {
            return ""; // No extension found or invalid file name
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
