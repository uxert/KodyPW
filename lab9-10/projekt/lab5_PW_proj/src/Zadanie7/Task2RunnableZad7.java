package Zadanie7;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Task2RunnableZad7 implements Runnable{
    Future taskToEnd;
    public Task2RunnableZad7(Future taskToEnd)
    {
        this.taskToEnd = taskToEnd;
    }
    public void run()
    {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        taskToEnd.cancel(true);
    }
}
