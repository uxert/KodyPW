package Zadanie4;

import java.util.concurrent.*;

public class test4 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService myExec = Executors.newSingleThreadExecutor();
        Future myFuture = myExec.submit(new TaskCallableZad4());
        boolean endedSuccesfully = false;
        long beginTime = System.currentTimeMillis();
        do {
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("Czy zadanie jest zakończone: " + myFuture.isDone());
            if (myFuture.isDone())
            {
                endedSuccesfully = true;
                break;
            }
        }while (System.currentTimeMillis() - beginTime <= 3500);
        if (!endedSuccesfully)
        {
            myFuture.cancel(true);
            System.out.println("Zadanie się nie wykonało - anulowano");
        }
        else
        {
            System.out.println("Zadanie wykonane poprawnie, zwróciło wynik: " + myFuture.get());
        }

        myExec.shutdownNow();

        System.out.println("Koniec");

    }
}
