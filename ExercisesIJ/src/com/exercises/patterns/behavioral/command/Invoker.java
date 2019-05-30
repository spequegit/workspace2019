package com.exercises.patterns.behavioral.command;

public class Invoker {

    public void executeCommand(HomeCommand command){
        command.execute();
    }

}
