package com.avinash;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class CallableDemo implements Callable<Integer> {
    @Override
    public Integer call() {
        throw new RuntimeException("runtime exception");
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //        com.avinash.CallableDemo callableDemo = new com.avinash.CallableDemo();
        //        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //        // get the custom exception from the callable from execution exception
        //        Future<Integer> result = executorService.submit(callableDemo);
        //
        //        try {
        //            System.out.println(result.get());
        //        } catch (ExecutionException ex) {
        //            System.out.println(ex.getCause().getClass() == RuntimeException.class);
        //        }
        //
        //        ArrayList<Integer> arrayList = new ArrayList<>();
        //        com.avinash.RunnableDemo runnableDemo = new com.avinash.RunnableDemo(6, arrayList);
        //        Future<List<Integer>> future =  executorService.submit(runnableDemo, arrayList);
        //        if(future.isDone()) {
        //            for(int num: arrayList)
        //                System.out.println(num);
        //        }
        //
        //        // runnable with exception
        //        Future future1 = executorService.submit((Runnable) () -> {
        //            System.out.println("Just a simple runnable");
        //        });
        //        future1.get();
        //
        //        executorService.shutdown();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
    }
}
