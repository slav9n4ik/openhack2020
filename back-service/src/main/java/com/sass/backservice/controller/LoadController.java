package com.sass.backservice.controller;

import com.sass.backservice.dto.ResponseDto;
import com.sass.backservice.service.LoadProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class LoadController {

    private final LoadProcessorService loadProcessorService;

    @PostMapping("/api/uploadImage")
    public ResponseEntity<ResponseDto> uploadPhotoNoType(@RequestParam("file") MultipartFile file) {
        log.info("Upload photo request");
        String result = loadProcessorService.processDefaultType(file);
        log.info("Upload photo result: {}", result);
        return new ResponseEntity<>(new ResponseDto(result), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/upload")
    @Deprecated
    public ResponseEntity<ResponseDto> uploadPhoto(@RequestParam("photo") MultipartFile file,
                                                   @RequestParam("type") @NotBlank String type) {
        log.info("Upload photo request, type: {}", type);
        String result = loadProcessorService.processWithType(file, type);
        log.info("Upload photo result: {}, type: {}", result, type);
        return new ResponseEntity<>(new ResponseDto(result), HttpStatus.BAD_REQUEST);
    }
}
