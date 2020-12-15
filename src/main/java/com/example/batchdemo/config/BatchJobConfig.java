package com.example.batchdemo.config;

import com.example.batchdemo.tasklet.PersonTasklet;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchJobConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public PersonTasklet tasklet(){
        return new PersonTasklet();
    }

    @Bean
    public Step step01() {
        return stepBuilderFactory
                .get("step01")
                .tasklet(tasklet())
                .build();
    }
}
