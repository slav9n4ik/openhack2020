package com.sass.backservice.execute.parser;

import com.sass.backservice.dto.ResultDto;
import org.springframework.stereotype.Service;

@Service
public class ResultParser implements Parser {
    @Override
    public ResultDto parse(String result) {
        String[] values = result.split(",");
        String serialNumber = values[0].split("ID:")[1].trim();
        String value = values[1].split("Value:")[1].trim();
        return new ResultDto(serialNumber, value);
    }
}
