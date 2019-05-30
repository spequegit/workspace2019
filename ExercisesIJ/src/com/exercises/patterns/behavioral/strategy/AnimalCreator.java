package com.exercises.patterns.behavioral.strategy;

public class AnimalCreator {

    private AnimalCreationStrategy strategy;

    public Animal createAnimal() {
        return strategy.createAnimal();
    }

    public void setCreationStrategy(AnimalCreationStrategy strategy) {
        this.strategy = strategy;
    }

}