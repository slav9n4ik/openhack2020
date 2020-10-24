package com.sass.backservice.utils;

import com.sass.backservice.exceptions.FileProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static java.nio.file.Files.*;

public class FileUtils {
    public static void copyMultipartWithReplace(MultipartFile file, Path target) {
        try {
            copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileProcessingException(e.getMessage());
        }
    }

    public static void createDir(Path target) {
        try {
            if (!exists(target)) {
                createDirectories(target);
            }
        } catch (IOException e) {
            throw new FileProcessingException(e.getMessage());
        }
    }

    public static void addPermissions(Path target) {
        if (exists(target)) {
            File file = new File(target.toString());
            file.setExecutable(true);
            file.setWritable(true);
        }
    }

    public static void deleteIfExists(Path target) {
        try {
            Files.deleteIfExists(target);
        } catch (IOException e) {
            throw new FileProcessingException(e.getMessage());
        }
    }
}
