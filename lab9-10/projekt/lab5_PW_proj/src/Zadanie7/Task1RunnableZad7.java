package Zadanie7;

import java.util.concurrent.TimeUnit;

public class Task1RunnableZad7 implements Runnable{
    String taskName;
    public Task1RunnableZad7(String taskName)
    {
        this.taskName = taskName;
    }
    public void run()
    {
        while(true)
        {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Przerwano task " + taskName);
                throw new RuntimeException(e);
            }
            System.out.printf("%s : %s\n", taskName, Thread.currentThread().getName());
        }


    }
}
