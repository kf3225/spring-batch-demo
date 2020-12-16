package com.example.batchdemo.tasklet;

import com.example.batchdemo.model.Members;
import com.example.batchdemo.model.Person;
import com.example.batchdemo.service.MembersService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@StepScope
public class PersonTasklet implements Tasklet {

    @Autowired
    private ItemStreamReader<Person> reader;

    @Autowired
    private MembersService membersService;

    @Value("#{jobParameters['filename']}")
    private String params;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        Person person;
        List<Person> personList = new ArrayList<>();

        ExecutionContext executionContext = chunkContext.getStepContext().getStepExecution().getExecutionContext();
        executionContext.put("input.filename", params);

        try {
            reader.open(executionContext);
            while ((person = reader.read()) != null) {
                personList.add(person);
            }
        } catch (ItemStreamException e) {
            throw new ItemStreamException("");
        }

        membersService.createMemberInfo(new Members(personList));

        return RepeatStatus.FINISHED;
    }
}
