package com.personal.memorylane.services;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    String uploadFile(MultipartFile file);
}
