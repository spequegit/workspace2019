package com.exercises.algorithms;

import java.util.Arrays;
import java.util.List;

public class Factorial {

    public Factorial() {
        int result = doFactorialA(5);
        System.out.println(result);
        result = doFactorialB(-1);
        System.out.println(result);
    }

    public int doFactorialA(int a) {
        int result = 1;
        while(a>0){
            result = a * result;
            a--;
        }
        return result;
    }

    public int doFactorialB(int a) {
        return a>0?a*doFactorialB(a-1):1;
    }

    public static void main(String[] args) {
        new Factorial();
    }
}