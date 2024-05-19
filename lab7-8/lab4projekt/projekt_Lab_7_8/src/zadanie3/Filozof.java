package zadanie3;

public class Filozof extends Thread{

    final int nr;
    final int powtorzenia;
    final Widelce stolik;

    public Filozof(int nr, int pow, Widelce wid)
    {
        this.nr = nr;
        this.powtorzenia = pow;
        stolik = wid;
    }

    void rozmyslanie() throws InterruptedException
    {
        Thread.sleep((long) (5+ Math.random() * 10));
    }

    void jedzenie() throws InterruptedException
    {
        Thread.sleep((long) (1 + 4 * Math.random()));
    }

    void dzialanieFilozofa() throws InterruptedException
    {
        for (int i = 0; i < powtorzenia; i++)
        {
            rozmyslanie();
            stolik.wez(nr, i);
            jedzenie();
            stolik.odloz(nr, i);
        }
    }

    public void run()
    {
        try {
            dzialanieFilozofa();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
