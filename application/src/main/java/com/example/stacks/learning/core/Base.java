package com.example.stacks.learning.core;

import java.io.FileNotFoundException;

public class Base extends Base2{

    public void test() throws FileNotFoundException {
        throw new FileNotFoundException();
    }
}