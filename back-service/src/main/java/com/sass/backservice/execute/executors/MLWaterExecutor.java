package com.sass.backservice.execute.executors;

import com.sass.backservice.exceptions.MLExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Path;

import static com.sass.backservice.execute.OutputReader.outputReader;

@Slf4j
@RequiredArgsConstructor
public class MLWaterExecutor implements MLExecutor {

    private final Path scriptPath;

    public static MLWaterExecutor mlWaterExecutor(Path path) {
        return new MLWaterExecutor(path);
    }

    @Override
    public String execute(Path photoPath) {
        log.info("ML Water script path: {}", scriptPath);
        ProcessBuilder processBuilder = new ProcessBuilder("python3", scriptPath.toString(), photoPath.toString());
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            return outputReader("Result:").getResults(process.getInputStream(), process.waitFor());
        } catch (IOException | InterruptedException e) {
            throw new MLExecutionException(e.getMessage());
        }
    }
}
