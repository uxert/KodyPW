package zadanie1;

import java.util.concurrent.Semaphore;

public class Semafory {

    Semaphore wolne;
    Semaphore zajete;
    Semaphore chron_j;
    Semaphore chron_k;

    public Semafory(int N)
    {
        wolne = new Semaphore(N);
        zajete = new Semaphore(0);
        chron_j = new Semaphore(1);
        chron_k = new Semaphore(1);
    }

}
