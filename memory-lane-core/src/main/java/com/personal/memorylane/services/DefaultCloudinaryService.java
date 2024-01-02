package com.personal.memorylane.services;

import com.cloudinary.Cloudinary;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DefaultCloudinaryService implements CloudinaryService {
    @Resource
    private Cloudinary cloudinary;

    @Value("${cloudinary.videos.folder:videos}")
    private String folderName;

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            HashMap<Object, Object> options = new HashMap<>();
            options.put("folder", folderName);
            Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
            String publicId = (String) uploadedFile.get("public_id");
            return cloudinary.url().secure(true).generate(publicId);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
