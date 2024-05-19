package zadanie3;
import java.util.concurrent.Semaphore;
//klasa uruchomieniowa
public class test {

    public static void main(String[] args) throws InterruptedException {

        //parametry
        final int m = 5; // liczba czytelnikow
        final int n = 2; // liczba pisarzy
        final int liczba_powtorzen = 100;
        final int pojemnosc_czytelni = 3;
        //tablica liczb przechowujaca 5 liczb, w kolejnosci:
        // cp, cc, pp, pc - oznaczenia z wykladow oraz ostatnia - czy ktorys Pisarz doszedl do konca.
        //jest to potrzebne aby uporac sie z sytuacja, w ktorej na koniec zadania czytelnicy stoja w kolejce, ale
        // nie ma kto ich wpuscic bo czytelnia jest pusta a wszyscy pisarze zakonczyli prace
        int[] liczby = new int[5];

        for (int i = 0; i < 5; i++)
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
            czytelnicy[i] = new Czytelnik(i, mySems, liczba_powtorzen, liczby, pojemnosc_czytelni);
        }
        for (int i = 0; i < n; i++)
        {
            pisarze[i] = new Pisarz(i, mySems, liczba_powtorzen, liczby, pojemnosc_czytelni);
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
