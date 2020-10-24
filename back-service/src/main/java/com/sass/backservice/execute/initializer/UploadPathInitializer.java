package com.sass.backservice.execute.initializer;

import lombok.RequiredArgsConstructor;
import java.nio.file.Path;

import static com.sass.backservice.utils.FileUtils.createDir;

@RequiredArgsConstructor
public class UploadPathInitializer {

    private final Path uploadPath;

    public static UploadPathInitializer uploadPathInitializer(Path uploadPath) {
        return new UploadPathInitializer(uploadPath);
    }

    public void init() {
        createDir(uploadPath);
        //addPermissions(uploadPath);
    }
}
