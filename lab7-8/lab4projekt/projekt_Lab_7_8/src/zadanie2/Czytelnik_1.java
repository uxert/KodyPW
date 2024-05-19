package zadanie2;

public class Czytelnik extends Thread{

    final int nr_czytelnika;
    final int powtorzenia;
    final Czytelnia instancjaCzytelni;

    public Czytelnik(final int nr, final Czytelnia instancjaCzytelni, final int powtorzenia)
    {
        this.instancjaCzytelni = instancjaCzytelni;
        nr_czytelnika = nr;
        this.powtorzenia = powtorzenia;
    }


    void czytanie() throws InterruptedException
    {
        Thread.sleep((long) (1 + 4 * Math.random()));
    }

    void sprawyWlasne() throws InterruptedException
    {
        Thread.sleep((long) (5 + Math.random() * 10));
    }

    void dzialanieCzytelnika() throws InterruptedException
    {
        for (int i = 0; i < powtorzenia; i++)
        {
            instancjaCzytelni.pocz_czytania(nr_czytelnika, i);
            czytanie();
            instancjaCzytelni.kon_czytania(nr_czytelnika, i);
            sprawyWlasne();
        }
    }

    public void run()
    {
        try {
            dzialanieCzytelnika();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
