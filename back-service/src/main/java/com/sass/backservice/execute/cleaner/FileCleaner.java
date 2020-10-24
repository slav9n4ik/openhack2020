package com.sass.backservice.execute.cleaner;

import lombok.RequiredArgsConstructor;

import java.nio.file.Path;

import static com.sass.backservice.utils.FileUtils.deleteIfExists;

@RequiredArgsConstructor
public class FileCleaner implements AutoCloseable {

    private final Path path;

    public static FileCleaner fileCleaner(Path path) {
        return new FileCleaner(path);
    }

    @Override
    public void close() {
        deleteIfExists(path);
    }
}
