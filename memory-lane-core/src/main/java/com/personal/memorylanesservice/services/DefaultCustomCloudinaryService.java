package com.personal.memorylanesservice.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.personal.memorylaneservice.dto.response.CloudinaryUpload;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class DefaultCustomCloudinaryService implements CustomCloudinaryService {
    @Resource
    private Cloudinary cloudinary;

    @Value("${cloudinary.videos.folder:videos}")
    private String folderName;

    @Override
    public CloudinaryUpload uploadFile(MultipartFile file) {
        try {
            HashMap<Object, Object> options = new HashMap<>();
            options.put("folder", folderName);
            Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
            //return uploadedFile;
            String publicId = (String) uploadedFile.get("public_id");
            String url =  cloudinary.url().secure(true).generate(publicId);

            CloudinaryUpload result = CloudinaryUpload.builder().publicId(publicId).url(url).build();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getImageUrl(String publicId) {
        try {
            Map resourceInfo = cloudinary.api().resource(publicId, ObjectUtils.emptyMap());
            if (resourceInfo.containsKey("url")) {
                return resourceInfo.get("url").toString();
            }
            return null;
        } catch (Exception ex) {
            log.info("Cannot fetch asset information for publicId {}", publicId, ex);
            throw new RuntimeException("Internal server error");
        }
    }

    @Override
    public void removeFile(String publicId) {
        try {
            cloudinary.api().deleteResources(Collections.singleton(publicId), ObjectUtils.emptyMap());
        } catch (Exception ex) {
            log.info("error removing resource with id {}", publicId, ex);
            throw new RuntimeException("Internal server error.");
        }

    }
}
