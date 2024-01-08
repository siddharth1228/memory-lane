package com.personal.memorylaneservice.controller;

import com.personal.memorylaneservice.core.CoreVideoData;
import com.personal.memorylaneservice.dto.request.VideoUploadRequest;
import com.personal.memorylanesservice.services.CustomCloudinaryService;
import com.personal.memorylanesservice.services.FileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/ve1")
public class FileMangerController {

    @Autowired
    private FileManagerService fileManagerService;


    @PostMapping("/upload-file")
    public ResponseEntity<CoreVideoData> uploadFile(@RequestParam(value = "title") final String title,
                                          @RequestParam(value = "description", required = false) final String description,
                                          @RequestParam(value = "file") MultipartFile file) {
        VideoUploadRequest videoUploadRequest = VideoUploadRequest.builder().description(description).title(title).build();
        CoreVideoData response = fileManagerService.upload(videoUploadRequest, file);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/get-all-videos")
    public ResponseEntity<List<CoreVideoData>> getAllVideos() {
        List<CoreVideoData> response = fileManagerService.getAllVideo();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-video")
    public ResponseEntity<CoreVideoData> getAllVideos(@RequestParam(value = "publicId") final String publicId) {
        CoreVideoData response = fileManagerService.getVideo(publicId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/remove-all-videos")
    public ResponseEntity<Void> removeAllVideos() {
        fileManagerService.removeAllVideos();
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
