package Zadanie2;

public class Test {

    static public char[] znaki = new char[]{'+','-','-','+','+'};
    public static void main (String[] args) throws InterruptedException
    {
        Peterson[] w = new Peterson[2];
        boolean czySynchronizacja = true;
        for (int k = 0; k < 2; k++)
        {
            w[k] = new Peterson(k+1,czySynchronizacja, znaki[k]);
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