package Zadanie3;

@SuppressWarnings("StatementWithEmptyBody")
public class Lamport extends Thread
{
    int nr;
    boolean czySynchr;
    int liczbaPowtorzen;
    char znak;
    static int liczbaWatkow = 5;
    static volatile boolean[] wybieranie = {false, false, false, false, false};
    static volatile int[] numerek = {0, 0, 0, 0, 0};
    public Lamport(int nr, boolean synchr, char znak, int n)
    {
        czySynchr = synchr;
        this.nr = nr;
        this.znak = znak;
        liczbaPowtorzen = n; // zadana liczba powtórzeń
    }

    void dzialanieNiesynchr()
    {
        for (int i = 0; i < liczbaPowtorzen; i++)
        {
            //sprawy własne
            try
            {
                Thread.sleep((long) (Math.random() * 9 + 1));
            } catch (InterruptedException e) {
                System.out.println("Niespodziewanie przerwano sleep");
            }

            //sekcja krytyczna
            System.out.println("Sekcja krytyczna wątku: Lamport- " + this.nr + ", nr powt.= " + i);
            for (int j = 0; j < 100; j++)
            {
                System.out.print(znak);
            }
            System.out.println();

        }
    }


    void dzialanieSynchr()
    {
        for (int i = 0; i < liczbaPowtorzen; i++)
        {
            //sprawy własne
            try
            {
                Thread.sleep((long) (Math.random() * 9 + 1));
            } catch (InterruptedException e) {
                System.out.println("Niespodziewanie przerwano sleep");
            }

            //sekcja wejściowa
            wybieranie[this.nr - 1] = true;
            numerek[this.nr - 1] = znajdzMax(numerek, liczbaWatkow) + 1;
            wybieranie[this.nr - 1] = false;

            for (int j = 0; j < liczbaWatkow; j++)
            {
                while (wybieranie[j])
                {
                    //czekaj
                }
                while (numerek[j] != 0  && porownajWekt2Elem(numerek[j], j, numerek[this.nr - 1], this.nr - 1))
                {
                    // czekaj
                }

            }


            //sekcja krytyczna
            System.out.println("Sekcja krytyczna wątku: Lamport- " + this.nr + ", nr powt.= " + i);
            for (int j = 0; j < 100; j++)
            {
                System.out.print(znak);
            }
            System.out.println();

            //sekcja wyjściowa

            numerek[this.nr - 1] = 0;
        }
    }



    public void run(){
        //System.out.println("Watek dekker- " + this.nr + " wszedł do run()");
        if (!czySynchr) dzialanieNiesynchr();
        else dzialanieSynchr();
    }

    public int znajdzMax(int[] tab, int n)
    {
        int mojMax = 0;
        for (int i = 0; i < n; i++)
        {
            if (tab[i] > mojMax) mojMax = tab[i];
        }
        return mojMax;
    }

    public boolean porownajWekt2Elem(int a1, int a2, int b1, int b2)
    {
        if (a1 < b1) return true;
        if (a1 > b1) return false;
        if (a2 < b2) return true;
        return false;

    }
}