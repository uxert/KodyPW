package zadanie2;

public class test2 {

    public static void main(String[] args) throws InterruptedException {
        final int m = 4;
        final int n = 2;

        final int l_pow_pis = 50;
        final int l_pow_czyt = l_pow_pis;

        final Czytelnik[] czytelnicy = new Czytelnik[m];
        final Pisarz[] pisarze = new Pisarz[n];

        final Czytelnia instancjaCzytelni = new Czytelnia();

        for (int i = 0; i < n; i++)
        {
            pisarze[i] = new Pisarz(i, instancjaCzytelni, l_pow_pis);
            pisarze[i].start();
        }

        for (int i = 0; i < m; i++)
        {
            czytelnicy[i] = new Czytelnik(i, instancjaCzytelni, l_pow_czyt);
            czytelnicy[i].start();
        }

        for (int i = 0; i < n; i++)
        {
            pisarze[i].join();
        }

        for (int i = 0; i < m; i++)
        {
            czytelnicy[i].join();
        }

        System.out.println("Wątki potomne zakończone, program wykonany poprawnie");

    }
}