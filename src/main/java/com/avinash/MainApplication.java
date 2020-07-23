package com.avinash;

public class MainApplication {
    public static void main(String[] args) {
        RunnableDemo runnableDemo = new RunnableDemo(6);
        Thread thread = new Thread(runnableDemo);
        thread.start();
        // runnable as functional interface
        thread = new Thread(() -> System.out.println("this runnable is shows as lambda interface"));
        thread.start();

        // starting a new thread via extending thread
        ThreadDemo threadDemo = new ThreadDemo(10);
        threadDemo.start();
    }
}
