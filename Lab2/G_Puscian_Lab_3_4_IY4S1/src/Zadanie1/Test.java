package Zadanie1;

public class Test {

    static public char[] znaki = new char[]{'+','-','-','+','+'};
    public static void main (String[] args) throws InterruptedException
    {
        Dekker[] w = new Dekker[2];
        int n = 10;
        boolean czySynchronizacja = true;
        for (int k = 0; k < 2; k++)
        {
            w[k] = new Dekker(k+1,czySynchronizacja, znaki[k], n);
        }
        for (int k = 0 ;k < 2; k++)
        {
            w[k].start();
        }
        for (int k = 0; k < 2; k++)
        {
            w[k].join();
        }
        System.out.println("Koniec");
    }
}
