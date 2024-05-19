package zadanie1;

import java.util.concurrent.Semaphore;

public class Konsument extends Thread{
    final int liczbaPowtorzen;
    final int N;
    int nrWatku;

    Semaphore wolne, zajete, chron_j, chron_k;
    Dane mojeDane;

    public Konsument(final int liczbaPowtorzen, final int N, int nrWatku, Semafory sem, Dane mojeDane)
    {
        this.liczbaPowtorzen = liczbaPowtorzen;
        this.N = N;
        this.nrWatku = nrWatku;
        this.mojeDane = mojeDane;
        wolne = sem.wolne;
        zajete = sem.zajete;
        chron_j = sem.chron_j;
        chron_k = sem.chron_k;
    }
    void konsumuj(String dana, int powtorzenie)
    {
        try {
            Thread.sleep((long) (2 + Math.random() * 10));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("[K-" + nrWatku + ", " + powtorzenie + "] >> " + dana);
    }

   void dzialanieKonsumenta()
   {
       for (int i = 1; i <= liczbaPowtorzen; i++)
       {
           try {
               zajete.acquire();
               chron_k.acquire();
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }

           String dana = mojeDane.buf[mojeDane.k];
           wolne.release();

           mojeDane.k = (mojeDane.k + 1) % N;
           chron_k.release();

           konsumuj(dana, i);

       }
   }

   public void run()
   {
       dzialanieKonsumenta();
   }

}
