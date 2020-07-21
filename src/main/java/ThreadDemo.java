import java.util.Random;

public class ThreadDemo extends Thread {
    private final int n;

    ThreadDemo(int n) {
        this.n = n;
    }
    @Override
    public void run() {
        for(int i = 0; i < n; i++)
            System.out.println(String.format("random with thread %d", new Random().nextInt(i + 1)));
    }
}
