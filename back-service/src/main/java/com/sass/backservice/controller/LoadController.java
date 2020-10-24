package com.sass.backservice.controller;

import com.sass.backservice.dto.ResultDto;
import com.sass.backservice.service.LoadProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class LoadController {

    private final LoadProcessorService loadProcessorService;

    @PostMapping("/api/uploadImage")
    public ResponseEntity<ResultDto> uploadPhotoNoType(@RequestParam("file") MultipartFile file) {
        log.info("Upload photo request");
        ResultDto result = loadProcessorService.process(file);
        log.info("Upload photo serialNumber: {}, value: {}", result.getSerialNumber(), result.getValue());
        return new ResponseEntity<>(result, OK);
    }
}
