package com.personal.memorylaneservice.controller;

import com.personal.memorylanesservice.services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ve1")
public class FileMangerController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "title") final String title,
                                          @RequestParam(value = "description", required = false) final String description,
                                          @RequestParam(value = "file") MultipartFile file) {

        String response = cloudinaryService.uploadFile(file);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
