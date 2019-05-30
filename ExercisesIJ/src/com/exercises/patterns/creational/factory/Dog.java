package com.exercises.patterns.creational.factory;

public class Dog implements Animal {
    @Override
    public void makeNoise() {
        System.out.println("hauuu");
    }
}
