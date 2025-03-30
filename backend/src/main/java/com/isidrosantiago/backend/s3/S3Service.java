package com.isidrosantiago.backend.s3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

import static software.amazon.awssdk.services.s3.model.ObjectCannedACL.PUBLIC_READ;

@Service
public class S3Service {

    @Autowired
    private S3Client s3;

    @Value("${aws.bucket}")
    private String bucket;

    @Value("${aws.region}")
    private String region;

    @Value("${aws.folder}")
    private String folder;

    public String storeObject(String filename, byte[] file) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
            .bucket(bucket)
            .key(folder + filename)
            .acl(PUBLIC_READ)
            .build();

        s3.putObject(objectRequest, RequestBody.fromBytes(file));
        return getPublicUrl(filename);
    }

    public byte[] getObject(String filename) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucket(bucket)
            .key(filename)
            .build();

        ResponseInputStream<GetObjectResponse> response = s3.getObject(getObjectRequest);

        try {
            return response.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String getPublicUrl(String filename) {
        return "https://" + bucket + ".s3." + region + ".amazonaws.com/" + folder + filename;
    }
}
