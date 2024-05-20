package Zadanie6;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class TaskCallableZad6 implements Callable<Integer> {
    String taskName;
    public TaskCallableZad6(String taskName)
    {
        this.taskName = taskName;
    }
    public Integer call() throws InterruptedException {
        int sleepTime = (int) (Math.random() * 3 + 2);
        TimeUnit.SECONDS.sleep(sleepTime);
        System.out.printf("%s : %s : %d\n",taskName, Thread.currentThread().getName(), sleepTime);
        Integer result = sleepTime;
        return result;
    }
}
