package com.sass.backservice.execute.executors;

import com.sass.backservice.dto.ResultDto;
import com.sass.backservice.exceptions.MLExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;

import static com.sass.backservice.execute.OutputReader.outputReader;

@Slf4j
@Service
public class DefaultMLExecutor implements MLExecutor {

    @Value("${file.default-script-path}")
    private Path mlScriptPath;
    private final String RESULT_CODE = "ID:";

    @Override
    public ResultDto execute(Path path) {
        log.info("ML script path: {}", mlScriptPath);
        ProcessBuilder processBuilder = new ProcessBuilder("python3", mlScriptPath.toString(), path.toString());
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            return parseResult(outputReader(RESULT_CODE).getResults(process.getInputStream(), process.waitFor()));
        } catch (IOException | InterruptedException e) {
            throw new MLExecutionException(e.getMessage());
        }
    }

    private ResultDto parseResult(String result) {
        String[] values = result.split(",");
        String serialNumber = values[0].split(RESULT_CODE)[1].trim();
        String value = values[1].split("Value:")[1].trim();
        return new ResultDto(serialNumber, value);
    }
}
