package zadanie2;

import java.util.concurrent.Semaphore;


public class Czytelnik extends Thread{

    Semaphore czyt;
    Semaphore pis;
    Semaphore chron;
    int nr_czytelnika;
    int liczba_powtorzen;

    int[] liczby;
    public Czytelnik(int nr, Semaphore[] sems, int liczba_powtorzen, int[] liczby)
    {
        nr_czytelnika = nr;
        czyt = sems[0];
        pis = sems[1];
        chron = sems[2];
        this.liczba_powtorzen = liczba_powtorzen;
        this.liczby = liczby;
    }

    void sprawyWlasne() {
        try {
            Thread.sleep((long) (5 + Math.random() * 10));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void czytanie()
    {
        try {
            Thread.sleep((long) (1 + Math.random() * 4));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void dzialanieCzytelnika() throws InterruptedException {
        for (int count = 0; count < liczba_powtorzen; count++)
        {

            //wlanse sprawy

            chron.acquire();
            System.out.printf(">>> [C-%d, %d] :: [%d %d %d %d]\n", nr_czytelnika + 1, count + 1, liczby[1], liczby[0], liczby[3], liczby[2]);
            if ((liczby[2] + liczby[3]) == 0)
            {
                liczby[1] ++;
                czyt.release();
            }
            else
            {
                liczby[0]++;
            }
            System.out.printf("<<< [C-%d, %d] :: [%d %d %d %d]\n", nr_czytelnika + 1, count + 1, liczby[1], liczby[0], liczby[3], liczby[2]);

            chron.release();

            czyt.acquire();
            //czytanie
            System.out.printf("==> [C-%d, %d] :: [%d %d %d %d]\n", nr_czytelnika + 1, count + 1, liczby[1], liczby[0], liczby[3], liczby[2]);
            czytanie();
            System.out.printf("<== [C-%d, %d] :: [%d %d %d %d]\n", nr_czytelnika + 1, count + 1, liczby[1], liczby[0], liczby[3], liczby[2]);


            chron.acquire();
            System.out.printf(">>> [C-%d, %d] :: [%d %d %d %d]\n", nr_czytelnika + 1, count + 1, liczby[1], liczby[0], liczby[3], liczby[2]);

            liczby[1]--;
            if (liczby[1] == 0)
            {
                if (liczby[2] > 0)
                {
                    liczby[3] = 1;
                    liczby[2]--;
                    pis.release();
                }
            }
            System.out.printf("<<< [C-%d, %d] :: [%d %d %d %d]\n", nr_czytelnika + 1, count + 1, liczby[1], liczby[0], liczby[3], liczby[2]);

            chron.release();
        }

    }
    public void run() {
        try {
            dzialanieCzytelnika();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
