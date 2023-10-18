package com.onlinestore.api.file;

import com.onlinestore.api.file.web.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileService {
    FileDto uploadSingle(MultipartFile file);
    List<FileDto> uploadMultiple(List<MultipartFile> fileList);
    FileDto findByName(String name) throws IOException;
}
