package com.avinash.tutorial;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

class Producer implements Runnable {
    private final BlockingQueueImpl blockingQueue;

    Producer(BlockingQueueImpl blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(20);
                blockingQueue.enqueue(new Random(50).nextInt());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("adding element");
        }
    }
}

class Consumer implements Runnable {
    private final BlockingQueueImpl blockingQueue;

    Consumer(BlockingQueueImpl blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                System.out.println("removing element" + blockingQueue.dequeue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class BlockingQueueImpl {
    private List<Integer> store;
    private int bound;

    BlockingQueueImpl(int bound) {
        this.store = new LinkedList<>();
        this.bound = bound;
    }

    public synchronized void enqueue(Integer element) throws InterruptedException {
        while(store.size() == bound)
            wait();
        store.add(element);
        if(store.size() == 1)
            notifyAll();
    }

    public synchronized Integer dequeue() throws InterruptedException {
        while(store.size() == 0)
            wait();
        System.out.println("unblocked");
        if(store.size() == bound)
            notifyAll();
        return store.remove(0);
    }

    public static void main(String[] args) {
        BlockingQueueImpl blockingQueue = new BlockingQueueImpl(5);
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);
        ExecutorService executorService = newFixedThreadPool(4);
        executorService.execute(producer);
        executorService.execute(consumer);
    }
}
