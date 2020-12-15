package com.example.batchdemo.config;

import com.example.batchdemo.model.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.support.SingleItemPeekableItemReader;
import org.springframework.batch.item.support.builder.SingleItemPeekableItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class CsvReaderConfig {

    private static final String INPUT_FILE = "input.filename";
    @Autowired
    SingleItemPeekableItemReaderBuilder<Person> singleItemPeekableItemReaderBuilder;
    @Autowired
    FlatFileItemReaderBuilder<Person> flatFileItemReaderBuilder;

    @Bean
    SingleItemPeekableItemReader<Person> reader(@Value("#{jobExecutionContext['" + INPUT_FILE + "']}") String filename) {

        FlatFileItemReader<Person> flatFileItemReader = flatFileItemReaderBuilder
                .name("personReader")
                .resource(new ClassPathResource(filename))
                .delimited()
                .delimiter(",")
                .names("firstName", "lastName", "age")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                    setTargetType(Person.class);
                }})
                .build();

        return singleItemPeekableItemReaderBuilder
                .delegate(flatFileItemReader)
                .build();
    }
}
