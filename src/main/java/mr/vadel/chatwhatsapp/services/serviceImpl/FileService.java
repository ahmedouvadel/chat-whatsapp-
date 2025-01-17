package mr.vadel.chatwhatsapp.services.serviceImpl;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;
import static java.lang.System.currentTimeMillis;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    @Value("${application.file.uploads.media-output-path}")
    private String uploadFilePath;
    public String saveFile(@Nonnull MultipartFile sourceFile,
                           @Nonnull  String userId) {
        final String fileUploadSubPath = "users" + separator + userId;
        return uploadFile(sourceFile, fileUploadSubPath);
    }

    private String uploadFile(@Nonnull MultipartFile sourceFile,
                              @Nonnull String fileUploadSubPath) {
        final String finalUploadPath = uploadFilePath + separator + fileUploadSubPath;
        final File targetFolder = new File(finalUploadPath);
        if (!targetFolder.exists()) {
           boolean folderCreated =  targetFolder.mkdirs();
              if (!folderCreated) {
                log.warn("Failed to create folder: {}", finalUploadPath);
                return null;
              }
        }
        final String fileExtension = getFileExtension(sourceFile.getOriginalFilename());
        final String targetFilePath = finalUploadPath + separator + currentTimeMillis() + fileExtension;
        Path targetPath = Paths.get(targetFilePath);
        try {
            Files.write(targetPath, sourceFile.getBytes());
            return targetFilePath;
        } catch (IOException e) {
            log.error("Failed to save file: {}", targetFilePath, e);
        }
        return null;
    }

    private String getFileExtension(String filename) {
        if(filename == null || filename.isEmpty()) {
            return "";
        }
        int lastIndexOfDot = filename.lastIndexOf(".");
        if(lastIndexOfDot == -1) {
            return "";
        }
        return filename.substring(lastIndexOfDot + 1).toLowerCase();
    }
}
