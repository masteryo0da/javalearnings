package com.avinash.tutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RunnableDemo implements Runnable {
    private final int n;
    private final Random random;
    private final List<Integer> list;

    public RunnableDemo(int n) {
        this(n, new ArrayList<>());
    }

    public RunnableDemo(int n, List<Integer> list) {
        this.n = n;
        random = new Random();
        this.list = list;
    }

    @Override
    public void run() {
        for(int i = 0; i < n; i++) {
            System.out.println(String.format("random number is : %d", random.nextInt(i + 1)));
            this.list.add(i + random.nextInt(i + 1));
        }
    }
}
