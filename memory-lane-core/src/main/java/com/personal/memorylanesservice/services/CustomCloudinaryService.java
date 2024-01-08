package com.personal.memorylanesservice.services;

import com.personal.memorylaneservice.dto.response.CloudinaryUpload;
import org.springframework.web.multipart.MultipartFile;

public interface CustomCloudinaryService {

    CloudinaryUpload uploadFile(MultipartFile file);

    String getImageUrl(String publicId);

    void removeFile(String publicId);
}
