package com.isidrosantiago.backend.controllers;

import com.isidrosantiago.backend.common.PageResponse;
import com.isidrosantiago.backend.requests.FileUploadRequest;
import com.isidrosantiago.backend.responses.CustomResponse;
import com.isidrosantiago.backend.responses.FileResponse;
import com.isidrosantiago.backend.responses.FileUploadResponse;
import com.isidrosantiago.backend.services.FileService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/file")
@Tag(name = "File")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping(value = "", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadFile(
        @Valid @ModelAttribute FileUploadRequest request
    ) {
        String url = fileService.uploadFile(request.getFile());
        CustomResponse<FileUploadResponse> response = new CustomResponse<>(
            "success",
            "file has been save in S3",
            new FileUploadResponse(url)
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getFiles(
        @RequestParam(name = "page", defaultValue = "0", required = false) int page,
        @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        PageResponse<FileResponse> files = fileService.getFiles(page, size);
        CustomResponse<PageResponse<FileResponse>> response = new CustomResponse<>("success", files);
        return ResponseEntity.ok(response);
    }
}
