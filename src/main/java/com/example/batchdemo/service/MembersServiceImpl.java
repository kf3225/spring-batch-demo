package com.example.batchdemo.service;

import com.example.batchdemo.model.Members;
import com.example.batchdemo.model.Person;
import com.example.batchdemo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MembersServiceImpl implements MembersService {

    private final MemberRepository repository;

    @Override
    public int createMemberInfo(Members members) {
        List<Person> adultMembers = members.getAdultMembers();

        int result = repository.create(adultMembers);
        return result;
    }
}
