package com.sass.backservice.dto;

import lombok.Data;

import java.time.*;

@Data
public class ResponseDto {
    private final String result;
    private final LocalDateTime time = LocalDateTime.now();
}
