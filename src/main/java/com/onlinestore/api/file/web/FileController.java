package com.onlinestore.api.file.web;

import com.onlinestore.api.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    @PostMapping("/single")
    public FileDto uploadSingle(@RequestPart MultipartFile file){
        return fileService.uploadSingle(file);
    }
    @PostMapping("/multiple")
    public List<FileDto> uploadMultiple(@RequestPart List<MultipartFile> fileList){
        return fileService.uploadMultiple(fileList);
    }
}
