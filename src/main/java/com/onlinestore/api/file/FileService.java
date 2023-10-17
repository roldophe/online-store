package com.onlinestore.api.file;

import com.onlinestore.api.file.web.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileService {
    FileDto uploadSingle(MultipartFile file);
    List<FileDto> uploadMultiple(List<MultipartFile> fileList);
}
