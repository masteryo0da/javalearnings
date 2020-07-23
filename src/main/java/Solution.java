import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

class Incrementer {
    private int counter = 0;

    synchronized void increment() {
        this.counter++;
    }

    synchronized int getValue() {
        return this.counter;
    }
}

class DemoRunnable implements Runnable {
    private Incrementer incrementer;
    private int turn;
    private int currentNumber;
    private ReentrantLock lock;

    DemoRunnable(Incrementer incrementer, int turn, ReentrantLock lock) {
        this.incrementer = incrementer;
        this.turn = turn;
        this.currentNumber = turn + 1;
        this.lock = lock;
    }

    @Override
    public synchronized void run() {
        while(true) {
            if(incrementer.getValue() % 3 == turn) {
                lock.lock();
                System.out.println(this.currentNumber);
                this.currentNumber += 3;
                this.incrementer.increment();
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Incrementer incrementer = new Incrementer();
        ReentrantLock lock = new ReentrantLock();
        DemoRunnable runnable1 = new DemoRunnable(incrementer, 0, lock);
        DemoRunnable runnable2 = new DemoRunnable(incrementer, 1, lock);
        DemoRunnable runnable3 = new DemoRunnable(incrementer, 2, lock);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(runnable1);
        executorService.execute(runnable2);
        executorService.execute(runnable3);
    }
}