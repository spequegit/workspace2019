package com.exercises.callable;

import java.util.Random;
import java.util.concurrent.Callable;

public class SomethingCallable implements Callable<String> {
    @Override
    public String call() throws Exception {

        int millis = 1000 + new Random().nextInt(4000);
        System.out.println(millis);
        Thread.sleep(millis);
        return "result "+millis;
    }
}
