package com.example.batchdemo.repository;

import com.example.batchdemo.model.Members;
import com.example.batchdemo.model.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberRepository {

    int create(List<Person> adultList);

}
