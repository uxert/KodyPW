package Zadanie2;

import java.util.concurrent.*;


public class test2 {


    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService myExecutor = Executors.newScheduledThreadPool(2);
        final long creationTime = System.currentTimeMillis();
        myExecutor.schedule(new Task("Task nr 1", creationTime), 3, TimeUnit.SECONDS);
        myExecutor.scheduleAtFixedRate(new Task("Task nr 2", creationTime), 0, 2, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(10);
        myExecutor.shutdown();


        //sprawdza, co się stanie po zamknięciu executora
        try {
            myExecutor.execute(new Task("Task after shutdown", creationTime));
        } catch (RejectedExecutionException exc)
        {
            System.out.println("\nPrzy próbie wykonania zadania na zamkniętym executorze otrzymano:");
            exc.printStackTrace();
            System.out.println();
        }

        //czeka max 6 sekund aż zadania się wykonają, jeżeli nie zdążą, to siłą je zamyka
        myExecutor.awaitTermination(6, TimeUnit.SECONDS);
        System.out.println("koniec");
    }
}
