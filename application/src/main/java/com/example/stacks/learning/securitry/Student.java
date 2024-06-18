package com.example.stacks.learning.securitry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "name")
public class Student {

    private Integer id;
    private String name;

    public static Student of(Integer id, String name){
        return new Student(id, name);
    }
}
