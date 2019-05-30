package com.exercises.callable;

import java.util.concurrent.*;

public class ExampleCallableApplication {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("start");
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("newSingleThreadExecutor");
        Future<String> result = executorService.submit(new SomethingCallable());
        Future<String> result2 = executorService.submit(new SomethingCallable());
        Future<String> result3 = executorService.submit(new SomethingCallable());
        Future<String> result4 = executorService.submit(new SomethingCallable());

        Thread.sleep(5000);     // dajemy czas na zakonczenie sie watkow

        System.out.println("submit");
        System.out.println(result.get());
        System.out.println(result2.get(100, TimeUnit.MILLISECONDS));     // dzieki temu teraz zdazy odczytac w 200 ms
        System.out.println(result3.get());
        System.out.println(result4.get());
        System.out.println("get");

        executorService.shutdown();
        System.out.println("shutdown");
    }
}
