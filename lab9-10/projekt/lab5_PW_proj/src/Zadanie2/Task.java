package Zadanie2;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

    final String name;
    final long creationTime;
    public Task(String name, long creationTime)
    {
        this.name = name;
        this.creationTime = creationTime;
    }

    public void run()
    {
        int sleepTime = (int) (Math.random() * 5 + 1);
        System.out.printf("<%d : %s : %s : %s\n",System.currentTimeMillis() - creationTime, name, sleepTime, Thread.currentThread().getName());
    try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf(">%d : %s : %s : %s\n",System.currentTimeMillis() - creationTime, name, sleepTime, Thread.currentThread().getName());
    }
}
