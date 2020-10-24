package com.sass.backservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface LoadProcessorService {
    String processWithType(MultipartFile file, String type);
    String processDefaultType(MultipartFile file);
}
