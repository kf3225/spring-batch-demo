package com.example.batchdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Person {

    /** 名前 */
    private String firstName;
    /** 苗字 */
    private String lastName;
    /** 年齢 */
    private String age;
}
