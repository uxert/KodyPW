package zadanie2;
import java.util.concurrent.Semaphore;

//klasa uruchomieniowa
public class test {

    public static void main(String[] args) throws InterruptedException {
        final int m = 2; // liczba czytelnikow
        final int n = 1; // liczba pisarzy
        final int liczba_powtorzen = 1;
        //tablica obiektow typu Integer przechowujaca 4 liczby, w kolejnosci:
        // cp, cc, pp, pc - oznaczenia z wykladow
        int[] liczby = new int[4];
        for (int i = 0; i < 4; i++)
        {
            liczby[i] = 0;
        }
        //tablica zawierajaca semafory, w kolejnosci, czyt, pis, chron
        Semaphore[] mySems = new Semaphore[3];
        mySems[0] = new Semaphore(0); // czyt
        mySems[1] = new Semaphore(0); // pis
        mySems[2] = new Semaphore(1); // binarny chron


        Czytelnik[] czytelnicy = new Czytelnik[m];
        Pisarz[] pisarze = new Pisarz[n];

        for (int i = 0; i < m; i++)
        {
            czytelnicy[i] = new Czytelnik(i, mySems, liczba_powtorzen, liczby);
        }
        for (int i = 0; i < n; i++)
        {
            pisarze[i] = new Pisarz(i, mySems, liczba_powtorzen, liczby);
        }

        for (int i = 0; i < n; i++)
        {
            pisarze[i].start();
        }
        for (int i = 0; i < m; i++)
        {
            czytelnicy[i].start();
        }

        for (int i =0; i < n; i++)
        {
            pisarze[i].join();
        }

        for (int i = 0; i < m; i++)
        {
            czytelnicy[i].join();
        }

        System.out.println("Koniec uruchomienia, program zakonczony poprawnie");
    }
}
