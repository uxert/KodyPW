package Zadanie5;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class    TaskCallableZad5 implements Callable<String> {
    String taskName;
    public TaskCallableZad5(String taskName)
    {
        this.taskName = taskName;
    }
    public String call() throws InterruptedException {
        int sleepTime = (int) (Math.random() * 3 + 2);
        TimeUnit.SECONDS.sleep(sleepTime);
        return taskName;
    }
}
