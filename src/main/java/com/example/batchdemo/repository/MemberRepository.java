package com.example.batchdemo.repository;

import com.example.batchdemo.model.Members;

public interface MemberRepository {

    int insertListByCriteria(Members members);
}
