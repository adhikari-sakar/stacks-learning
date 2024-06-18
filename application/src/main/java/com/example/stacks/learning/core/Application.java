package com.example.stacks.learning.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Application extends Base implements Marker {

//    @Override
//    public void test() throws FileNotFoundException {
//        throw new FileNotFoundException();
//    }

    public static String[] varargs(int x, String... args) {
        return args;
    }

    public static void main(String... args) throws FileNotFoundException {
        Set<StringBuilder> stringBuilders = new TreeSet<>();
        stringBuilders.add(new StringBuilder("a"));
        stringBuilders.add(new StringBuilder("b"));
        stringBuilders.add(new StringBuilder("c"));
        System.out.println(stringBuilders);
    }
}
