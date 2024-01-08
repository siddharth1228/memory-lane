package com.personal.memorylanesservice.services;

import com.personal.memorylaneservice.core.CoreVideoData;
import com.personal.memorylaneservice.dto.request.VideoUploadRequest;
import com.personal.memorylaneservice.dto.response.CloudinaryUpload;
import com.personal.memorylaneservice.mappers.VideoDataMapper;
import com.personal.memorylaneservice.model.VideoData;
import com.personal.memorylaneservice.repo.VideosRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Log4j2
public class DefaultFileMangerService implements FileManagerService {

    @Autowired
    private CustomCloudinaryService customCloudinaryService;

    @Autowired
    private VideosRepository videosRepository;

    @Autowired
    private VideoDataMapper videoDataMapper;


    @Override
    public CoreVideoData upload(VideoUploadRequest request, MultipartFile file) {
        try {
            CloudinaryUpload cloudinaryUpload = customCloudinaryService.uploadFile(file);
            VideoData videoData  = VideoData.builder().publicId(cloudinaryUpload.getPublicId())
                    .url(cloudinaryUpload.getUrl())
                    .description(request.getDescription())
                    .title(request.getTitle())
                    .build();
            videosRepository.insert(videoData);
            return videoDataMapper.convert(videoData);
        } catch (Exception ex) {
            log.info("Cannot upload file", ex);
            throw new RuntimeException("Internal server error.");
        }
    }

    @Override
    public CoreVideoData getVideo(String publicId) {
        VideoData videoData = videosRepository.getVideoDataByPublicId(publicId);
        return videoDataMapper.convert(videoData);
    }

    @Override
    public List<CoreVideoData> getAllVideo() {
        List<VideoData> videoDataList = videosRepository.findAll();
        return videoDataMapper.convertList(videoDataList);
    }

    @Override
    public void removeAllVideos() {
        List<CoreVideoData> videoDataList = this.getAllVideo();
        videoDataList.forEach((video) -> {
            try {
                customCloudinaryService.removeFile(video.getPublicId());
                videosRepository.deleteVideoDataByPublicId(video.getPublicId());
            } catch (Exception ex) {
                log.info("cannot delete resource with id {}", video.getPublicId(), ex);
                throw new RuntimeException("Internal server error.");
            }

        });
    }


}
