package zadanie1;

public class test1 {
    public static void main (String[] args) throws InterruptedException {
        final int N = 10;
        final int m = 4; //liczba producentów
        final int n = 5; //liczba konsumentów

        final int liczbaPowtorzenKonsumentow = 60;
        final int liczbaPowtorzenProducentow = liczbaPowtorzenKonsumentow * n / m;

        KlasaMonitora1 monitor = new KlasaMonitora1(N);

        final producent[] producenci = new producent[m];
        final konsument[] konsumenci = new konsument[n];

        for (int i = 0; i < m; i++)
        {
            producenci[i] = new producent(i, liczbaPowtorzenProducentow, monitor);
        }
        for (int i = 0; i < n; i++)
        {
            konsumenci[i] = new konsument(i, liczbaPowtorzenKonsumentow, monitor);
        }

        for (int i = 0; i < m ; i++)
        {
            producenci[i].start();
        }
        for (int i = 0; i < n; i++)
        {
            konsumenci[i].start();
        }

        for (int i = 0; i < m; i++)
        {
            producenci[i].join();
        }
        for (int i = 0; i < n; i++)
        {
            konsumenci[i].join();
        }

        System.out.println("Wszystkie wątki potomne zakończone, poprawnie wykonano program");

    }

}
