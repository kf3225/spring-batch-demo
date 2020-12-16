package com.example.batchdemo.model;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.util.Assert;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ToString
@RequiredArgsConstructor
public class Members {

    @NonNull
    private final List<Person> personList;

    public List<Person> getAdultMembers() {
        Predicate<Person> isAdult = p -> {
            Assert.notNull(p.getAge(), String.format("年齢がnullです : %s", p.toString()));
            Assert.isTrue(!"".equals(p.getAge()), String.format("年齢が空です : %s", p.toString()));
            return Integer.parseInt(p.getAge()) >= 20;
        };
        // 年齢が20以上のメンバーを返す
        return personList.stream().filter(isAdult).collect(Collectors.toList());
    }
}
