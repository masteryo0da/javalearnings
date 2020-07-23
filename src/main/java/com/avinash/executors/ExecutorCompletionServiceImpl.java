package com.avinash.executors;

import java.util.concurrent.*;

public class ExecutorCompletionServiceImpl {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ExecutorCompletionService<String> completionService =
                new ExecutorCompletionService<>(executorService);

        Callable<String> callable1 = () -> {
            Thread.sleep(3000);
            return "first callable";
        };
        Callable<String> callable2 = () -> {
            Thread.sleep(1000);
            return "first callable";
        };
        Callable<String> callable3 = () -> "this is third callable";

        completionService.submit(callable1);
        completionService.submit(callable2);
        completionService.submit(callable3);
        for(int i = 0; i < 3; i++) {
            Future<String> result = completionService.take();
            System.out.println(result.get());
        }

    }
}
