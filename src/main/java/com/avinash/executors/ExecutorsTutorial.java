package com.avinash.executors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorsTutorial {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Callable<String> callable1 = () -> {
            Thread.sleep(3000);
            System.out.println("completed tasks");
            return "first callable";
        };
        Callable<String> callable2 = () -> "this is second callable";
        Callable<String> callable3 = () -> "this is third callable";
        String result = executorService.invokeAny(Arrays.asList(callable2, callable3));
        System.out.println(result);
        List<Future<String>> resultSet = executorService.invokeAll(Arrays.asList(callable2, callable3));
        for(Future<String> res: resultSet) {
            try {
                System.out.println(res.get());
            } catch (ExecutionException ex) {
                System.out.println("reason for exception" + ex.getCause());
            }
        }
        executorService.submit(callable1);
        executorService.submit(callable2);
        executorService.shutdown();
        executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
    }
}
