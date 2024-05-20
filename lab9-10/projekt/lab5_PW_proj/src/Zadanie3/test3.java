package Zadanie3;

import java.util.concurrent.*;

public class test3 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService myExec = Executors.newSingleThreadExecutor();

        Future future1 = myExec.submit(new TaskCallableZad3());
        while(!future1.isDone())
        {
            System.out.printf("Czy zadanie jest ukończone: %s\n", future1.isDone());
            TimeUnit.MILLISECONDS.sleep(500);
        }
        System.out.printf("Zadanie zwróciło wynik: %s\n\n", future1.get());
        myExec.shutdown();
        myExec.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("Koniec");
    }

}
