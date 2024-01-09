package io.innodev.blogapp.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface IFileService {
    public String uploadImage(String path, MultipartFile multipartFile) throws IOException;

    public InputStream getResource(String path, String fileName) throws FileNotFoundException;
}
