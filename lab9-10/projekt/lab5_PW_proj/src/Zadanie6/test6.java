package Zadanie6;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class test6 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Set<Callable<Integer>> myCallables = new HashSet<Callable<Integer>>();
        for (int i = 1; i <= 4; i++) {
            myCallables.add(new TaskCallableZad6("Task nr " + i));
        }
        ExecutorService myExec = Executors.newFixedThreadPool(4);
        List<Future<Integer>> myFutures = myExec.invokeAll(myCallables);

        Integer answer = 0;
        for (int i = 0; i < myFutures.size(); i++)
        {
            answer += myFutures.get(i).get(); // pierwszy get wyjmuje konkretny futures z listy wszystkich futuresów
            // drugi get wyjmuje z konkretnego futuresa zwracaną wartość
        }
        System.out.println("Suma wynosi: " + answer);
        myExec.shutdown();
        myExec.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println("Koniec");

    }
}
