package zadanie1;

public class producent extends Thread{

    KlasaMonitora1 dane;
    String elem;
    final int powtorzenia, nr_producenta;

    public producent(int nr_producenta,final int powtorzenia,  KlasaMonitora1 dane)
    {
        this.powtorzenia = powtorzenia;
        this.dane = dane;
        this.nr_producenta = nr_producenta;
    }

    void dzialanieProducenta() throws InterruptedException
    {
        int liczba;
        for (int i = 0 ; i < powtorzenia; i++)
        {
            //produkcja danych
            Thread.sleep((long) (1 + Math.random() * 9));
            liczba =(int) (Math.random() * 99);
            elem = String.format("Dana=[P-%d, %d, %d]", nr_producenta, i + 1, liczba);

            //wstawienie do bufora
            dane.wstaw(elem);
        }
    }

    public void run()
    {
        try {
            dzialanieProducenta();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
