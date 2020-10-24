package com.sass.backservice.execute.executors;

import com.sass.backservice.dto.ResultDto;
import com.sass.backservice.exceptions.MLExecutionException;
import com.sass.backservice.execute.parser.Parser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;

import static com.sass.backservice.execute.OutputReader.outputReader;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultMLExecutor implements MLExecutor {

    @Value("${file.default-script-path}")
    private Path mlScriptPath;
    private final Parser parser;

    @Override
    public ResultDto execute(Path path) {
        log.info("ML script path: {}", mlScriptPath);
        ProcessBuilder processBuilder = new ProcessBuilder("python3", mlScriptPath.toString(), path.toString());
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            return parser.parse(outputReader("ID:").getResults(process.getInputStream(), process.waitFor()));
        } catch (IOException | InterruptedException e) {
            throw new MLExecutionException(e.getMessage());
        }
    }
}
