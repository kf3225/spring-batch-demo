package com.example.batchdemo.service;

import com.example.batchdemo.model.Members;
import com.example.batchdemo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MembersServiceImpl implements MembersService {

    private final MemberRepository repository;

    @Override
    public int insert(Members members) {
        Members over20Members = members.getAdultMembers();

        int result = repository.insertListByCriteria(over20Members);
        return result;
    }
}
