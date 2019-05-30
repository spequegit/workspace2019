package com.exercises.patterns.behavioral.command;

public class MainApplication {

    public static void main(String[] args) {


        Invoker invoker = new Invoker();

        invoker.executeCommand(new HomeCommandIncreaseTemperature());
        invoker.executeCommand(new HomeCommandCloseAllWindows());
        invoker.executeCommand(new HomeCommandLightOn());

    }


}
