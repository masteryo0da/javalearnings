package com.avinash.tutorial;

public class ThreadWithException extends Thread {
    @Override
    public void run() {
        throw new NullPointerException();
    }

    public static void main(String[] args) {
        Thread thread = new ThreadWithException();
        UncaughtExceptionHandler handler = (t, e) ->
                System.out.println(String.format("Exception in thread %s cause is %s"
                , t.getName(), e));
        thread.setUncaughtExceptionHandler(handler);
        thread.start();

        // un checked exception with runnable
        thread = new Thread(() -> {
            throw new RuntimeException("now it is exception time");
        });
        thread.setUncaughtExceptionHandler(handler);
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        while(thread.getState() != State.TERMINATED)
            System.out.println(thread.getState());
        System.out.println(thread.getState());
    }
}
