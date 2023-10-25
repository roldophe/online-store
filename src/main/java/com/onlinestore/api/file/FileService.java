package com.onlinestore.api.file;

import com.onlinestore.api.file.web.FileDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    Resource downloadByName(String name);

    FileDto uploadSingle(MultipartFile file);

    List<FileDto> uploadMultiple(List<MultipartFile> files);

    FileDto findByName(String name) throws IOException;

    void deleteAll();

    void deleteByName(String name);

    List<FileDto> findAll() throws IOException;
}
