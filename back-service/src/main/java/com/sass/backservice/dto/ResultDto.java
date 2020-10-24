package com.sass.backservice.dto;

import lombok.Data;

@Data
public class ResultDto {
    private final String serialNumber;
    private final String value;

    public String getSerialNumber() {
        return serialNumber.isEmpty() ? null : serialNumber;
    }
}
