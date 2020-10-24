package com.sass.backservice.config;

import com.sass.backservice.enums.CounterType;
import com.sass.backservice.execute.executors.MLExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.util.EnumMap;

import static com.sass.backservice.enums.CounterType.*;
import static com.sass.backservice.execute.executors.DefaultMLExecutor.defaultMLExecutor;
import static com.sass.backservice.execute.executors.MLElectricityExecutor.mlElectricityExecutor;
import static com.sass.backservice.execute.executors.MLWaterExecutor.mlWaterExecutor;

@Configuration
public class GeneralConfiguration {

    @Value("${file.python-water-script-path}")
    private Path mlWaterScriptPath;

    @Value("${file.python-electricity-script-path}")
    private Path mlElectricityScriptPath;

    @Value("${file.default-script-path}")
    private Path defaultMlScriptPath;


    @Bean
    public EnumMap<CounterType, MLExecutor> mlExecutorMap() {
        EnumMap<CounterType, MLExecutor> map = new EnumMap<>(CounterType.class);
        map.put(WATER, mlWaterExecutor(mlWaterScriptPath));
        map.put(ELECTRICITY, mlElectricityExecutor(mlElectricityScriptPath));
        map.put(DEFAULT, defaultMLExecutor(defaultMlScriptPath));
        return map;
    }
}
