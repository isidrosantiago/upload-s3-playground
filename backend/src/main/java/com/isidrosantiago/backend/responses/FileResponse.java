package com.isidrosantiago.backend.responses;

import java.util.UUID;

public class FileResponse {
    private UUID id;
    private String originalFilename;
    private String url;
    private String contentType;

    public FileResponse() {
    }

    public FileResponse(UUID id, String originalFilename, String url, String contentType) {
        this.id = id;
        this.originalFilename = originalFilename;
        this.url = url;
        this.contentType = contentType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
