package com.exercises.patterns.behavioral.command;

public class HomeCommandIncreaseTemperature implements HomeCommand {


    @Override
    public void execute() {
        System.out.println("Increasing temperature...");
    }
}
