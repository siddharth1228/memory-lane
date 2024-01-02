package com.personal.memorylaneservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class FileMangerController {

    public ResponseEntity<Map> uploadFile(@RequestParam(value = "title") final String title,
                                          @RequestParam(value = "description", required = false) final String description,
                                          @RequestBody MultipartFile file) {
        return null;
    }
}
