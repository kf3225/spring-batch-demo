package com.example.batchdemo.service;

import com.example.batchdemo.model.Members;

public interface MembersService {

    /**
     * メンバーを挿入する
     *
     * @param members
     * @return insert成功数
     */
    int createMemberInfo(Members members);
}
