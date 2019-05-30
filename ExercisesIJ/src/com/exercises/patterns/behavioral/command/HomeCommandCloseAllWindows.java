package com.exercises.patterns.behavioral.command;

public class HomeCommandCloseAllWindows implements HomeCommand {

    @Override
    public void execute() {
        System.out.println("Closing all windows...");
    }
}
