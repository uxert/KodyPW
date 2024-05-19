package zadanie2;

public class Pisarz extends Thread{

    final int nr_pisarza;
    final Czytelnia instancjaCzytelni;
    final int powtorzenia;

    public Pisarz(final int nr, final Czytelnia instancjaCzytelni, final int powtorzenia)
    {
        this.instancjaCzytelni = instancjaCzytelni;
        nr_pisarza = nr;
        this.powtorzenia = powtorzenia;
    }

    void pisanie() throws InterruptedException {
        Thread.sleep((long) (1 + 4 * Math.random()));
    }

    void sprawyWlasne() throws InterruptedException
    {
        Thread.sleep((long) (5 + Math.random() * 10));
    }

    void dzialaniePisarza() throws InterruptedException
    {
        for (int i = 0; i < powtorzenia; i++)
        {
            instancjaCzytelni.pocz_pisania(nr_pisarza, i);
            pisanie();
            instancjaCzytelni.kon_pisania(nr_pisarza, i);
            sprawyWlasne();
        }
    }

    public void run()
    {
        try {
            dzialaniePisarza();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }




}
