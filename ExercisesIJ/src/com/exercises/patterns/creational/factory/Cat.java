package com.exercises.patterns.creational.factory;

public class Cat implements Animal {
    @Override
    public void makeNoise() {
        System.out.println("miauuu");
    }
}
