package com.exercises.patterns.creational.factory;

import com.exercises.patterns.behavioral.command.HomeCommandCloseAllWindows;
import com.exercises.patterns.behavioral.command.HomeCommandIncreaseTemperature;
import com.exercises.patterns.behavioral.command.HomeCommandLightOn;
import com.exercises.patterns.behavioral.command.Invoker;

public class MainApplication {

    public static void main(String[] args) {

        AnimalFactory animalFactory = new AnimalFactory();

        Animal pies = animalFactory.createAnimal("pies");

        pies.makeNoise();

    }


}
