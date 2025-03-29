package com.isidrosantiago.backend.requests;

import com.isidrosantiago.backend.validations.ValidFile;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadRequest {
    @ValidFile
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}