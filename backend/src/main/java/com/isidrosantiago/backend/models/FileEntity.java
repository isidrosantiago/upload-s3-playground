package com.isidrosantiago.backend.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "file")
public class FileEntity extends BaseEntity {
    @Id
    private UUID id;
    private String originalFilename;
    private String url;
    private String contentType;

    public FileEntity() {
    }

    public FileEntity(UUID id, String originalFilename, String url, String contentType) {
        this.id = id;
        this.originalFilename = originalFilename;
        this.url = url;
        this.contentType = contentType;
    }

    public FileEntity(String originalFilename, String contentType) {
        this.originalFilename = originalFilename;
        this.contentType = contentType;
    }

    public FileEntity(LocalDateTime createdDate, LocalDateTime lastModifiedDate, UUID id, String originalFilename, String url, String contentType) {
        super(createdDate, lastModifiedDate);
        this.id = id;
        this.originalFilename = originalFilename;
        this.url = url;
        this.contentType = contentType;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public UUID getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
