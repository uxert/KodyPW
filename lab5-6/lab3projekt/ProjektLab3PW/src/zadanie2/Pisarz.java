package zadanie2;

import java.util.concurrent.Semaphore;

public class Pisarz extends Thread{

    int nr_pisarza;
    Semaphore czyt;
    Semaphore pis;
    Semaphore chron;
    int liczba_powtorzen;
    int[] liczby;
    public Pisarz(int nr, Semaphore[] sems, int liczba_powtorzen, int[] liczby)
    {
        czyt = sems[0];
        pis = sems[1];
        chron = sems[2];
        nr_pisarza = nr;
        this.liczba_powtorzen = liczba_powtorzen;
        this.liczby = liczby;
    }

    void sprawyWlasne()
    {
        try {
            Thread.sleep((long) (5 + Math.random() * 10));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    void pisanie()
    {
        try {
            Thread.sleep((long) (1 + Math.random() * 4));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void dzialaniePisarza() throws InterruptedException {
        for (int count = 0; count < liczba_powtorzen; count++)
        {
            chron.acquire();
            System.out.printf(">>> [P-%d, %d] :: [%d %d %d %d]\n", nr_pisarza + 1, count + 1, liczby[1], liczby[0], liczby[3], liczby[2]);

            if (liczby[1] + liczby[3] == 0)
            {
                liczby[3] = 1;
                pis.release();
            }
            else
            {
                liczby[2]++;
            }

            System.out.printf("<<< [P-%d, %d] :: [%d %d %d %d]\n", nr_pisarza + 1, count + 1, liczby[1], liczby[0], liczby[3], liczby[2]);
            chron.release();

            pis.acquire();
            System.out.printf("==> [P-%d, %d] :: [%d %d %d %d]\n", nr_pisarza + 1, count + 1, liczby[1], liczby[0], liczby[3], liczby[2]);
            pisanie();
            System.out.printf("<== [P-%d, %d] :: [%d %d %d %d]\n", nr_pisarza + 1, count + 1, liczby[1], liczby[0], liczby[3], liczby[2]);

            chron.acquire();
            System.out.printf(">>> [P-%d, %d] :: [%d %d %d %d]\n", nr_pisarza + 1, count + 1, liczby[1], liczby[0], liczby[3], liczby[2]);

            liczby[3] = 0;
            if (liczby[0] > 0)
            {
                while (liczby[0] >0)
                {
                    liczby[1]++;
                    liczby[0]--;
                    czyt.release();
                }
            }
            else if (liczby[2] > 0)
            {
                liczby[3] = 1;
                liczby[2]--;
                pis.release();
            }
            System.out.printf("<<< [P-%d, %d] :: [%d %d %d %d]\n", nr_pisarza + 1, count + 1, liczby[1], liczby[0], liczby[3], liczby[2]);
            chron.release();
        }
    }
    public void run() {
        try {
            dzialaniePisarza();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
