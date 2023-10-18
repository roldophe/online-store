package com.onlinestore.api.file.web;

import com.onlinestore.api.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping(value = "/single", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileDto uploadSingle(@RequestPart MultipartFile file) {
        return fileService.uploadSingle(file);
    }

    @PostMapping(value = "/multiple", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<FileDto> uploadMultiple(@RequestPart List<MultipartFile> fileList) {
        return fileService.uploadMultiple(fileList);
    }

    @GetMapping("/{name}")
    public FileDto findByName(@PathVariable String name) throws IOException {
        return fileService.findByName(name);
    }
}
