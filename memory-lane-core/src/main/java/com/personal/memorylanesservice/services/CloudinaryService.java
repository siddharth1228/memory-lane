package com.personal.memorylanesservice.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CloudinaryService {

    String uploadFile(MultipartFile file);
}
