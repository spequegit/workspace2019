package com.exercises.patterns.behavioral.strategy;

public class MainApplication {

    public static void main(String[] args) {


        AnimalCreator creator = new AnimalCreator();

        creator.setCreationStrategy(new WildAnimalCreationStartegy());

        Animal animal = creator.createAnimal();

        animal.sayWhoYouAre();

        creator.setCreationStrategy(new HomeAnimalCreationStrategy());

        Animal animal1 = creator.createAnimal();

        animal1.sayWhoYouAre();


    }


}
