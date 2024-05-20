package Zadanie3;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class TaskCallableZad3 implements Callable<String> {
    public String call() throws InterruptedException {
        int sleepTime = 2;
        TimeUnit.SECONDS.sleep(sleepTime);
        return Thread.currentThread().getName();
    }

}
