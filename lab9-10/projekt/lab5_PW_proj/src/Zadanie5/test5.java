package Zadanie5;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class test5 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Set<Callable<String>> myCallables = new HashSet<Callable<String>>();
        for (int i = 1 ; i <= 4; i++)
        {
            myCallables.add(new TaskCallableZad5("Task nr " + i));
        }
        ExecutorService myExec = Executors.newFixedThreadPool(4);
        String result = myExec.invokeAny(myCallables);
        System.out.println("Wynik = " + result);

        myExec.shutdown();
        myExec.awaitTermination(1, TimeUnit.SECONDS); // teoretycznie to nie musi tu być ale daje pewność,
        // że executor się zamknął
        System.out.println("Koniec");

    }
}
