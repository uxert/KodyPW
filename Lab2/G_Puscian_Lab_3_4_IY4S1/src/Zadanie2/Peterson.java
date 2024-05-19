package Zadanie2;


public class Peterson extends Thread
{
    int nr;
    static volatile boolean[] chce = new boolean[] {true, true};
    boolean czySynchr;
    static volatile int czyjaKolej = 1;
    int liczbaPowtorzen;
    char znak;
    public Peterson(int nr, boolean synchr, char znak)
    {
        czySynchr = synchr;
        this.nr = nr;
        this.znak = znak;
        liczbaPowtorzen = 10; // zadana liczba powtórzeń
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
            System.out.println("Sekcja krytyczna wątku: Peterson- " + this.nr + ", nr powt.= " + i);
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
            chce[this.nr - 1] = false;
            czyjaKolej = (this.nr % 2) + 1;
            while ((!chce[this.nr % 2]) && czyjaKolej == (this.nr % 2) + 1)
            {
                //czekaj
            }

            //sekcja krytyczna
            System.out.println("Sekcja krytyczna wątku: Peterson- " + this.nr + ", nr powt.= " + i);
            for (int j = 0; j < 100; j++)
            {
                System.out.print(znak);
            }
            System.out.println();

            //sekcja wyjściowa
            chce[this.nr - 1] = true;
        }
    }



    public void run(){
        //System.out.println("Watek dekker- " + this.nr + " wszedł do run()");
        if (!czySynchr) dzialanieNiesynchr();
        else dzialanieSynchr();
    }
}