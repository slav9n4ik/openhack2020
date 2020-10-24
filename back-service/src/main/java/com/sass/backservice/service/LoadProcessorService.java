package com.sass.backservice.service;

import com.sass.backservice.dto.ResultDto;
import org.springframework.web.multipart.MultipartFile;

public interface LoadProcessorService {
    ResultDto process(MultipartFile file);
}
