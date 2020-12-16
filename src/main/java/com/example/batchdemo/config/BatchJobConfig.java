package com.example.batchdemo.config;

import com.example.batchdemo.tasklet.PersonTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@EnableBatchProcessing
public class BatchJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final PersonTasklet personTasklet;

    @Bean
    public Job personJob(Step step01) {
        return jobBuilderFactory.get("personJob")
                .flow(step01)
                .end()
                .build();
    }

    @Bean
    public Step step01() {
        return stepBuilderFactory.get("personStep")
                .tasklet(personTasklet)
                .build();
    }
}
