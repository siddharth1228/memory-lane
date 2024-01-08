package com.personal.memorylanesservice.services;

import com.personal.memorylaneservice.core.CoreVideoData;
import com.personal.memorylaneservice.dto.request.VideoUploadRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileManagerService {

    CoreVideoData upload(VideoUploadRequest request, MultipartFile file);

    CoreVideoData getVideo(String publicId);

    List<CoreVideoData> getAllVideo();

    void removeAllVideos();

}
