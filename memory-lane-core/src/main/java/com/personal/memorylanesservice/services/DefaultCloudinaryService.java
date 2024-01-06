package com.personal.memorylanesservice.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
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
            //return uploadedFile;
            String publicId = (String) uploadedFile.get("public_id");
            return cloudinary.url().secure(true).generate(publicId);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getImageUrl(String publicId) throws Exception {
        Map resourceInfo = cloudinary.api().resource(publicId, ObjectUtils.emptyMap());
        if (resourceInfo.containsKey("url")){
            return resourceInfo.get("url").toString();
        }
        return null;
    }
}
