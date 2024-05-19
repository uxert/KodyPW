
package Zadanie4;

import java.util.concurrent.*;
public class Sem extends Thread
{
    int nr;
    boolean czySynchr;
    int liczbaPowtorzen;
    char znak;
    Semaphore sem1;
    public Sem(int nr, boolean synchr, char znak, Semaphore sem, int n)
    {
        czySynchr = synchr;
        this.nr = nr;
        this.znak = znak;
        liczbaPowtorzen = n; // zadana liczba powtórzeń
        sem1 = sem;

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
            System.out.println("Sekcja krytyczna wątku: Sem-" + this.nr + ", nr powt.= " + i);
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
            try
            {
                sem1.acquire();
            } catch (InterruptedException e)
            {
                System.out.println("Niespodziewanie przerwano czekanie na semafor w wątku " + this.nr);
            }
            //sekcja krytyczna
            System.out.println("Sekcja krytyczna wątku: Sem-" + this.nr + ", nr powt.= " + i);
            for (int j = 0; j < 100; j++)
            {
                System.out.print(znak);
            }
            System.out.println();

            //sekcja wyjściowa
            sem1.release();

        }
    }



    public void run(){
        //System.out.println("Watek dekker- " + this.nr + " wszedł do run()");
        if (!czySynchr) dzialanieNiesynchr();
        else dzialanieSynchr();
    }
}