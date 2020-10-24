package com.sass.backservice.service;

import com.sass.backservice.dto.ResultDto;
import com.sass.backservice.enums.CounterType;
import com.sass.backservice.exceptions.CounterTypeException;
import com.sass.backservice.execute.cleaner.FileCleaner;
import com.sass.backservice.execute.executors.DefaultMLExecutor;
import com.sass.backservice.execute.executors.MLExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Objects;

import static com.sass.backservice.execute.cleaner.FileCleaner.fileCleaner;
import static com.sass.backservice.execute.initializer.UploadPathInitializer.uploadPathInitializer;
import static com.sass.backservice.utils.FileUtils.copyMultipartWithReplace;
import static com.sass.backservice.enums.CounterType.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoadProcessorServiceImpl implements LoadProcessorService {

    @Value("${file.upload-dir}")
    private Path uploadDir;

    private final MLExecutor mlExecutor;

    @Override
    public ResultDto process(MultipartFile file) {
        uploadPathInitializer(uploadDir).init();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileCleaner fileCleaner = fileCleaner(Path.of(uploadDir.toString(), fileName))) {
            log.info("Try to upload in {} with name: {}", uploadDir, fileName);
            copyMultipartWithReplace(file, uploadDir.resolve(fileName));
            return mlExecutor.execute(Path.of(uploadDir.toString(), fileName));
        }
    }
}