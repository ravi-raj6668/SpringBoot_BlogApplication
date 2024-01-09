package io.innodev.blogapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileService implements IFileService {
    @Override
    public String uploadImage(String path, MultipartFile multipartFile) throws IOException {
        String name = multipartFile.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String fileName = null;
        if (name != null) {
            fileName = randomId.concat(name.substring(name.lastIndexOf(".")));

            String filePath = path + File.separator + fileName;

            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            Files.copy(multipartFile.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        }
        return fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String filePath = path + File.separator + fileName;
        return new FileInputStream(filePath);
    }
}
