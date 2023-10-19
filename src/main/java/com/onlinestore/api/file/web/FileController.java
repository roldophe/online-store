package com.onlinestore.api.file.web;

import com.onlinestore.api.file.FileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping(value = "download/{name}")
    public ResponseEntity<?> download(@PathVariable String name) {
        Resource resource = fileService.downloadByName(name);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=" + resource.getFilename())
                .body(resource);
    }

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

    @GetMapping
    public List<FileDto> findAll() throws IOException {
        return fileService.findAll();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{name}")
    public void deleteByName(@PathVariable String name) {
        fileService.deleteByName(name);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping()
    public void delete() {
        fileService.deleteAll();
    }
}
