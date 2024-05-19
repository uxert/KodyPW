package zadanie1;

import java.util.concurrent.Semaphore;

public class Producent extends Thread {

    final int liczbaPowtorzen;
    final int N;
    int nrWatku;

    Semaphore wolne, zajete, chron_j, chron_k;
    Dane mojeDane;

    public Producent(final int liczbaPowtorzen, final int N, int nrWatku, Semafory sem, Dane mojeDane)
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

    String produkuj(int powtorzenie)
    {
        try {
            Thread.sleep((long) (1 + 9 * Math.random()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int liczba = (int) (Math.random() * 99);
        return "Dana=[P-" + nrWatku + ", " + powtorzenie + "., " + liczba +"]";


    }

    void dzialanieProducenta()  {
        for (int i = 1; i <= liczbaPowtorzen; i++)
        {
            String dana = produkuj(i);

            try {
                wolne.acquire();
                chron_j.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            mojeDane.buf[mojeDane.j] = dana;
            zajete.release();

            mojeDane.j = (mojeDane.j + 1) % N;
            chron_j.release();
        }
    }

    public void run()
    {
        dzialanieProducenta();
    }

}
