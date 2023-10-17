package com.onlinestore.api.file;

import com.onlinestore.api.file.web.FileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Value("${file.server-path}")
    private String serverPath;

    @Value("${file.base-uri}")
    private String fileBaseUri;

    @Override
    public FileDto uploadSingle(MultipartFile file) {
        //Get file extension
        int lastDotIndex = file.getOriginalFilename().lastIndexOf(".");
        String extension = file.getOriginalFilename().substring(lastDotIndex + 1);

        String name = UUID.randomUUID() + "." + extension;
        String uri = fileBaseUri + name;
        Long size = file.getSize();
        //Create file path
        Path path = Paths.get(serverPath + name);

        //Copy to serverPath
        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return FileDto.builder()
                .name(name)
                .extension(extension)
                .size(size)
                .uri(uri)
                .build();
    }

    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> fileList) {
        List<FileDto> uploadedFiles = new ArrayList<>();
        for (MultipartFile file : fileList) {
            FileDto fileDto = uploadSingle(file);
            uploadedFiles.add(fileDto);
        }
        return uploadedFiles;
    }
}
