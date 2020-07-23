package com.avinash.tutorial;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Semaphore {
    private boolean signal;
    Semaphore(boolean signal) {
        this.signal = signal;
    }

    public synchronized void signal() {
        this.signal = true;
        this.notify();
    }

    public synchronized void await() throws InterruptedException {
        while(!signal)
            wait();
        this.signal = false;
    }
}

class NumberPrinter implements Runnable {
    private Semaphore receiver;
    private Semaphore sender;
    private int currentValue;
    private int incrementValue;

    public NumberPrinter(Semaphore receiver, Semaphore sender, int currentValue, int incrementValue) {
        this.receiver = receiver;
        this.sender = sender;
        this.currentValue = currentValue;
        this.incrementValue = incrementValue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                this.receiver.await();
                this.currentValue = incrementValue + currentValue;
                System.out.println(currentValue);
                this.sender.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class NumberPrinting {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore1to2 = new Semaphore(false);
        Semaphore semaphore2to3 = new Semaphore(false);
        Semaphore semaphore3to1 = new Semaphore(true);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        NumberPrinter first = new NumberPrinter(semaphore3to1, semaphore1to2, -2, 3);
        NumberPrinter second = new NumberPrinter(semaphore1to2, semaphore2to3, -1, 3);
        NumberPrinter third = new NumberPrinter(semaphore2to3, semaphore3to1, 0, 3);
        executorService.execute(first);
        executorService.execute(second);
        executorService.execute(third);
        Thread.sleep(1000);
        executorService.shutdown();
//        System.out.println(7 & -7);
    }
}
