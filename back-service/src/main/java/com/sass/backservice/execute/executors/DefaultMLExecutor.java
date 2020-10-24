package com.sass.backservice.execute.executors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;

@Slf4j
@RequiredArgsConstructor
public class DefaultMLExecutor implements MLExecutor {

    private final Path scriptPath;

    public static DefaultMLExecutor defaultMLExecutor(Path path) {
        return new DefaultMLExecutor(path);
    }

    @Override
    public String execute(Path path) {
        return null;
    }
}
