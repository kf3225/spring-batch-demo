package com.example.batchdemo.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MembersTest {

    // 21歳
    private static final Person OVER_20 = Person.of("Taro", "Yamada", "21");
    // 20歳
    private static final Person JUST_20 = Person.of("Jiro", "Yamada", "20");
    // 19歳
    private static final Person UNDER_20 = Person.of("Saburo", "Yamada", "19");
    // -1歳
    private static final Person AGE_MINUS = Person.of("Shiro", "Yamada", "-1");
    // 空
    private static final Person AGE_EMPTY = Person.of("Goro", "Yamada", "");
    // null
    private static final Person AGE_NULL = Person.of("Rokuro", "Yamada", null);

    static public Stream<Arguments> testAddProvider() {
        return Stream.of(
                arguments(List.of(OVER_20, JUST_20), List.of(OVER_20, JUST_20), ""),
                arguments(List.of(OVER_20, UNDER_20), List.of(OVER_20), ""),
                arguments(List.of(AGE_MINUS), List.of(), ""),
                arguments(List.of(AGE_EMPTY), IllegalArgumentException.class, "年齢が空です : " + AGE_EMPTY.toString()),
                arguments(List.of(AGE_NULL), IllegalArgumentException.class, "年齢がnullです : " + AGE_NULL.toString())
        );
    }

    @ParameterizedTest
    @MethodSource("testAddProvider")
    public void testGetAdultMembers(List<Person> personList, Object expected, String msg) {
        try {
            Members members = new Members(personList);
            List<Person> actual = members.getAdultMembers();

            assertEquals(expected.toString(), actual.toString());
        } catch (IllegalArgumentException e) {
            assertAll(
                    () -> assertEquals(expected, e.getClass()),
                    () -> assertEquals(msg, e.getMessage())
            );
        }
    }
}
