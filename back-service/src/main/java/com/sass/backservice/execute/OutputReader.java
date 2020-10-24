package com.sass.backservice.execute;

import com.sass.backservice.exceptions.MLExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class OutputReader {

    private final String successCode;

    public static OutputReader outputReader(String successCode) {
        return new OutputReader(successCode);
    }

    public String getResults(InputStream inputStream, int exitCode) {
        return outputValidation(readOutput(inputStream), exitCode);
    }

    private Stream<String> readOutput(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .peek(line -> log.info("ML execution result [ {} ]", line));
    }

    private String outputValidation(Stream<String> resultOutput, int exitCode) {
        if (exitCode != 0) throw new MLExecutionException("The executions finished with exit code: " + exitCode);
        List<String> result = resultOutput
                .filter(line -> line.contains(successCode))
                .collect(Collectors.toList());
        if (result.size() == 0) throw new MLExecutionException("The executions finished with empty results");
        return result.get(0).split(successCode)[1].trim();
    }
}
