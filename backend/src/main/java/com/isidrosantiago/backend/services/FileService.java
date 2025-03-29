package com.isidrosantiago.backend.services;

import com.isidrosantiago.backend.common.PageResponse;
import com.isidrosantiago.backend.exceptions.InvalidFileExtensionException;
import com.isidrosantiago.backend.models.FileEntity;
import com.isidrosantiago.backend.repositories.FileRepository;
import com.isidrosantiago.backend.responses.FileResponse;
import com.isidrosantiago.backend.s3.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private S3Service s3Service;

    public String uploadFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        String extension = getFileExtension(contentType);

        if (extension.isEmpty()) {
            throw new InvalidFileExtensionException("The file must have a valid extension.");
        }

        UUID uuid = UUID.randomUUID();
        String filename = uuid + extension;

        try {
            byte[] fileBytes = file.getBytes();
            String url = s3Service.storeObject(filename, fileBytes);

            FileEntity newFile = new FileEntity(uuid, originalFilename, url, contentType);
            fileRepository.save(newFile);

            return url;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public PageResponse<FileResponse> getFiles(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<FileEntity> files = fileRepository.findAll(pageable);
        return pageResponse(files);
    }

    private String getFileExtension(String contentType) {
        if (contentType == null || contentType.isEmpty()) return "";

        int slashIdx = contentType.lastIndexOf("/");
        if (slashIdx == -1) return "";

        return "." + contentType.substring(slashIdx + 1).toLowerCase();
    }

    private PageResponse<FileResponse> pageResponse(Page<FileEntity> files) {
        List<FileResponse> fileResponse = files.stream()
            .map(FileService::getFileResponse)
            .toList();

        return new PageResponse<>(
            fileResponse,
            files.getNumber(),
            files.getSize(),
            files.getTotalElements(),
            files.getTotalPages(),
            files.isFirst(),
            files.isLast()
        );
    }

    private static FileResponse getFileResponse(FileEntity f) {
        return new FileResponse(
            f.getId(),
            f.getOriginalFilename(),
            f.getUrl(),
            f.getContentType()
        );
    }
}
