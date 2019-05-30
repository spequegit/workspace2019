package com.exercises.patterns.creational.factory;

public class AnimalFactory {
    public Animal createAnimal(String type){
        switch(type){
            case "kot":
                return new Cat();
            case "pies":
                return new Dog();
        }
        return null;
    }
}
