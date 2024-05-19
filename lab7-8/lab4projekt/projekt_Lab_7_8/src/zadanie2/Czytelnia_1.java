package zadanie2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Czytelnia {

    int licz_czyt = 0;
    int licz_pis = 0;
    int czyt_pocz = 0;
    int pis_pocz = 0;

    final Lock zamek = new ReentrantLock();
    final Condition czytelnicy = zamek.newCondition();
    final Condition pisarze = zamek.newCondition();

    public Czytelnia()
    {
        //nie przewidziano żadnych parametrów do użycia w konstruktorze
    }

    public void pocz_czytania(int nr, int powtorzenie) throws InterruptedException {
        zamek.lock();
        System.out.printf(">>> (1)[C-%d, %d] :: [licz_czyt=%d, licz_czyt_pocz=%d, licz_pis=%d, licz_pis_pocz=%d]\n",
                nr, powtorzenie, licz_czyt, czyt_pocz, licz_pis, pis_pocz);
        if (licz_pis + pis_pocz > 0)
        {
            czyt_pocz++;
            czytelnicy.await();
            czyt_pocz--;
        }
        licz_czyt++;
        System.out.printf(">>> (2)[C-%d, %d] :: [licz_czyt=%d, licz_czyt_pocz=%d, licz_pis=%d, licz_pis_pocz=%d]\n",
                nr, powtorzenie, licz_czyt, czyt_pocz, licz_pis, pis_pocz);
        zamek.unlock();
    }

    public void kon_czytania(int nr, int powtorzenie)
    {
        zamek.lock();
        System.out.printf("<<< (1)[C-%d, %d] :: [licz_czyt=%d, licz_czyt_pocz=%d, licz_pis=%d, licz_pis_pocz=%d]\n",
                nr, powtorzenie, licz_czyt, czyt_pocz, licz_pis, pis_pocz);
        licz_czyt--;
        if (licz_czyt == 0)
        {
            pisarze.signal();
        }
            System.out.printf("<<< (2)[C-%d, %d] :: [licz_czyt=%d, licz_czyt_pocz=%d, licz_pis=%d, licz_pis_pocz=%d]\n",
                nr, powtorzenie, licz_czyt, czyt_pocz, licz_pis, pis_pocz);
        zamek.unlock();
    }

    public void pocz_pisania(int nr, int powtorzenie) throws InterruptedException
    {
        zamek.lock();
        System.out.printf("==> (1)[P-%d, %d] :: [licz_czyt=%d, licz_czyt_pocz=%d, licz_pis=%d, licz_pis_pocz=%d]\n",
                nr, powtorzenie, licz_czyt, czyt_pocz, licz_pis, pis_pocz);
        if (licz_czyt + licz_pis > 0)
        {
            pis_pocz++;
            pisarze.await();
            pis_pocz--;
        }
        licz_pis = 1;
        System.out.printf("==> (2)[P-%d, %d] :: [licz_czyt=%d, licz_czyt_pocz=%d, licz_pis=%d, licz_pis_pocz=%d]\n",
                nr, powtorzenie, licz_czyt, czyt_pocz, licz_pis, pis_pocz);
        zamek.unlock();
    }

    public void kon_pisania(int nr, int powtorzenie)
    {
        zamek.lock();
        System.out.printf("<== (1)[P-%d, %d] :: [licz_czyt=%d, licz_czyt_pocz=%d, licz_pis=%d, licz_pis_pocz=%d]\n",
                nr, powtorzenie, licz_czyt, czyt_pocz, licz_pis, pis_pocz);

        licz_pis = 0;
        if (czyt_pocz > 0)
        {
            czytelnicy.signalAll();
        }
        else
        {
            pisarze.signal();
        }
        System.out.printf("<== (2)[P-%d, %d] :: [licz_czyt=%d, licz_czyt_pocz=%d, licz_pis=%d, licz_pis_pocz=%d]\n",
                nr, powtorzenie, licz_czyt, czyt_pocz, licz_pis, pis_pocz);

        zamek.unlock();
    }

}
