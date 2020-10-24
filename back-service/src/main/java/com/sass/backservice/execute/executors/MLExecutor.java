package com.sass.backservice.execute.executors;

import com.sass.backservice.dto.ResultDto;

import java.nio.file.Path;

public interface MLExecutor {
    ResultDto execute(Path path);
}
