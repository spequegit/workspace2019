package com.exercises.patterns.behavioral.strategy;

public class HomeAnimalCreationStrategy implements AnimalCreationStrategy {
    @Override
    public Animal createAnimal() {
        Animal animal = new Animal();
        animal.setName("Pies");
        animal.setWeight(25);
        animal.setNumberOfLegs(4);
        return animal;
    }
}
