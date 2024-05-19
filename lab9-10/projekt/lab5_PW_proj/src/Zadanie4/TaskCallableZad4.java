package Zadanie4;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class TaskCallableZad4 implements Callable<String> {
    public String call() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        int sleepTime = (int) (Math.random() * 3 + 2);
        TimeUnit.SECONDS.sleep(sleepTime);
        return String.format("%d",System.currentTimeMillis() - beginTime);
    }
}
