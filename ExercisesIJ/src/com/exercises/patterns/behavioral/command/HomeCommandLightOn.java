package com.exercises.patterns.behavioral.command;

public class HomeCommandLightOn implements HomeCommand {

    @Override
    public void execute() {
        System.out.println("Turning lights on...");
    }
}
