package Zadanie7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class test7 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService myExec = Executors.newFixedThreadPool(2);
        Future myFuture1 = myExec.submit(new Task1RunnableZad7("Task nr 1 - wypisujący"));
        Future myFuture2 = myExec.submit(new Task2RunnableZad7(myFuture1));

        myExec.shutdown();
        myExec.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Koniec");
    }

}
