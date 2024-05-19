package zadanie3;

import java.util.concurrent.Semaphore;


public class Czytelnik extends Thread{

    Semaphore czyt;
    Semaphore pis;
    Semaphore chron;
    int nr_czytelnika;
    int liczba_powtorzen;
    int K;
    int[] liczby;
    public Czytelnik(int nr, Semaphore[] sems, int liczba_powtorzen, int[] liczby, int k)
    {
        nr_czytelnika = nr;
        czyt = sems[0];
        pis = sems[1];
        chron = sems[2];
        this.liczba_powtorzen = liczba_powtorzen;
        this.liczby = liczby;
        this.K = k;
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
            if (((liczby[2] + liczby[3]) == 0) && (liczby[1] + liczby[3] < K)) // trzeba sprawdzic nie tylko czy nie ma pisarzy
                //lecz tutaj trzeba sprawdzic takze czy w czytelni w ogole jest jeszcze miejsce
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

            //trzeba dac czytelnikowi po skonczeniu mozliwosc wpuszczenia kolejnego czytelnika jesli czytelnia jest pusta
            // i w kolejce zostali sami czytelnicy, juz zadnego pisarza
            //w przeciwnym przypadku przy ograniczonych rozmiarach czytelni (gdy czytelnicy sie nie mieszcza na raz)
            //zadanie moze sie nie skonczyc, bo czytelnikow z poczekalni nikt nie bedzie mogl wpuscic gdy
            //wszyscy pisarze juz sie zakoncza i zadnego czytelnika w czytelni nie bedzie
            if ((liczby[1] + liczby[3] == 0) && liczby[0] > 0 && liczby[2] == 0 && liczby[4] == 1)
            {
                liczby[1]++;
                liczby[0]--;
                czyt.release();
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
