package zadanie1;

import java.util.concurrent.locks.Condition;

public class konsument extends Thread{

    final KlasaMonitora1 dane;
    String buf;
    final ZmienialnyString danaKonsumenta;
    final int powtorzenia, nr_konsumenta;

    public konsument(int nr_konsumenta,final int powtorzenia, final KlasaMonitora1 dane)
    {
        this.powtorzenia = powtorzenia;
        this.dane = dane;
        this.danaKonsumenta = new ZmienialnyString();
        this.nr_konsumenta = nr_konsumenta;
    }

    void dzialanieKonsumenta() throws InterruptedException {
        for (int i  = 0 ; i < powtorzenia; i++)
        {
            dane.pobierz(danaKonsumenta);

            //konsumuj
            buf = danaKonsumenta.zwrocString();
            Thread.sleep((long) (2 + Math.random() * 10));
            System.out.printf("[K-%d, %d] >> %s\n", nr_konsumenta, i + 1, buf);
        }
    }

    public void run()
    {
        try {
            dzialanieKonsumenta();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
