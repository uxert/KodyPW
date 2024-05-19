package Zadanie4;
import java.util.concurrent.*;

public class Test {

    static public char[] znaki = new char[]{'+','-','-','+','+'};
    public static void main (String[] args) throws InterruptedException
    {
        Sem[] w = new Sem[5];
        Semaphore mySem = new Semaphore(1);
        boolean czySynchronizacja = true;
        for (int k = 0; k < 5; k++)
        {
            w[k] = new Sem(k+1,czySynchronizacja, znaki[k], mySem);
        }
        for (int k = 0 ;k < 5; k++)
        {
            w[k].start();
        }
        for (int k = 0; k < 5; k++)
        {
            w[k].join();
        }
        System.out.println("Koniec");
    }
}