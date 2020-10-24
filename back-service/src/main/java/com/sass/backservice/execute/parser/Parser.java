package com.sass.backservice.execute.parser;

import com.sass.backservice.dto.ResultDto;

public interface Parser {
    ResultDto parse(String result);
}
