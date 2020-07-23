package com.avinash.tutorial;

public class ThreadStop implements Runnable {
    private boolean stop = false;

    public synchronized void stop() {
        this.stop = true;
    }

    private synchronized boolean continueRunning() {
        return !this.stop;
    }
    @Override
    public void run() {
        while (continueRunning()) {
            System.out.println("Just running in thread : " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadStop threadStop = new ThreadStop();
        Thread thread1 = new Thread(threadStop);
        Thread thread2 = new Thread(threadStop);
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
        threadStop.stop();
    }
}
