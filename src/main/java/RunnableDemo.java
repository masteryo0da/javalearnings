import java.util.Random;

public class RunnableDemo implements Runnable {
    private final int n;
    private final Random random;

    public RunnableDemo(int n) {
        this.n = n;
        random = new Random();
    }

    @Override
    public void run() {
        for(int i = 0; i < n; i++)
            System.out.println(String.format("random number is : %d", random.nextInt(i + 1)));
    }
}
