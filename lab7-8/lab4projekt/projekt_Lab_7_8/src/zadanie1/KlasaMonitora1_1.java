package zadanie1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KlasaMonitora1 {
    final int N; // rozmiar bufora
    int wej;
    int wyj;
    int licz;
    String[] pula;
    final Lock zamek = new ReentrantLock();

    final Condition pusty = zamek.newCondition();
    final Condition pelny = zamek.newCondition();


    public KlasaMonitora1(final int bufSize)
    {
        wej = 0;
        wyj = 0;
        licz = 0;
        N = bufSize;
        pula = new String[N];



    }

    public void wstaw(String elem) throws InterruptedException {
        zamek.lock();
        if (licz == N)
        {
            pelny.await();
        }
        pula[wej] = elem;
        wej = (wej + 1) % N;
        licz += 1;
        pusty.signal();
        zamek.unlock();



    }

    //trzeba bylo utworzyc klase ZmienalnyString poniewaz funkcje w monitorze nie powinny zwracac wartosci
    //a trzeba producentowi jakos przekazac wartosc z bufora
    public void pobierz(ZmienialnyString danaKonsumenta) throws InterruptedException {
        zamek.lock();
        if (licz == 0) {
            pusty.await();
        }
        danaKonsumenta.ustawString(pula[wyj]);
        wyj = (wyj + 1) % N;
        licz -= 1;
        pelny.signal();
        zamek.unlock();

    }
}
