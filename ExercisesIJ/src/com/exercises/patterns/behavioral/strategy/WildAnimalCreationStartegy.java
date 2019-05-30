package com.exercises.patterns.behavioral.strategy;

public class WildAnimalCreationStartegy implements AnimalCreationStrategy {
    @Override
    public Animal createAnimal() {
        Animal animal = new Animal();
        animal.setName("Antylopa");
        animal.setWeight(70);
        animal.setNumberOfLegs(4);
        return animal;
    }
}
