package Zadanie3;
public class Test {

    static public char[] znaki = new char[]{'+','-','-','+','+'};
    public static void main (String[] args) throws InterruptedException
    {
        int n = 10;
        int nWatkow = 5;
        boolean czySynchronizacja = true;
        Lamport[] w = new Lamport[nWatkow];
        for (int k = 0; k < nWatkow; k++)
        {
            w[k] = new Lamport(k+1,czySynchronizacja, znaki[k], n);
        }
        for (int k = 0 ;k < nWatkow; k++)
        {
            w[k].start();
        }
        for (int k = 0; k < nWatkow; k++)
        {
            w[k].join();
        }
        System.out.println("Koniec");
    }
}
